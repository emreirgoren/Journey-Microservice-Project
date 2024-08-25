package com.patika.basket_service.service.Impl;

import com.patika.basket_service.client.payment.service.PaymentClientService;
import com.patika.basket_service.dto.request.*;
import com.patika.basket_service.producer.KafkaProducer.KafkaProducer;
import com.patika.basket_service.producer.KafkaProducer.LogTemplate;
import com.patika.basket_service.producer.RabbitProducer.RabbitProducer;
import com.patika.basket_service.producer.RabbitProducer.model.Notification;
import com.patika.basket_service.producer.RabbitProducer.model.enums.NotificationType;
import com.patika.basket_service.repository.BasketRepository;
import com.patika.basket_service.client.TicketSearchClient;
import com.patika.basket_service.converter.JourneyConverter;
import com.patika.basket_service.dto.response.BasketResponse;
import com.patika.basket_service.dto.response.GenericResponse;
import com.patika.basket_service.exception.BasketException;
import com.patika.basket_service.exception.ExceptionMessages;
import com.patika.basket_service.model.Basket;
import com.patika.basket_service.model.Journey;
import com.patika.basket_service.model.Ticket;
import com.patika.basket_service.model.enums.Gender;
import com.patika.basket_service.model.enums.UserType;
import com.patika.basket_service.service.BasketService;
import com.patika.basket_service.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final JwtUtils jwtUtils;
    private final BasketRepository basketRepository;
    private final TicketSearchClient ticketSearchClient;
    private final KafkaProducer kafkaProducer;
    private final PaymentClientService paymentClientService;
    private final RabbitProducer rabbitProducer;

    public BasketServiceImpl(JwtUtils jwtUtils, BasketRepository basketRepository, TicketSearchClient ticketSearchClient, KafkaProducer kafkaProducer, PaymentClientService paymentClientService, RabbitProducer rabbitProducer) {
        this.jwtUtils = jwtUtils;

        this.basketRepository = basketRepository;
        this.ticketSearchClient = ticketSearchClient;
        this.kafkaProducer = kafkaProducer;
        this.paymentClientService = paymentClientService;
        this.rabbitProducer = rabbitProducer;
    }

    public GenericResponse<BasketResponse> addToBasket(BasketRequest basketRequest, String token) {

        String email = jwtUtils.extractEmail2(token);
        Journey journey = ticketSearchClient.getTicket(basketRequest.getTicketCode());
        Ticket ticket = JourneyConverter.journeyToTicket(journey);

        if (journey.getAvailableTicket() < basketRequest.getQuantity()) {
            throw new BasketException(ExceptionMessages.NOT_ENOUGH_TICKET);
        }

        if (basketRepository.findByEmail(email) == null) { // kullanıcının sepeti yoksa
            Basket newBasket = new Basket();
            newBasket.setEmail(email);
            basketRepository.save(newBasket);
        }
        Basket basket = basketRepository.findByEmail(email);
        ticket.setBasket(basket);
        List<Ticket> ticketList = basket.getTicketList();

        long ticketCount = ticketList.stream()
                .filter(t -> t.getTicketCode().equals(basketRequest.getTicketCode()))
                .count();

        if (jwtUtils.extractUserType(token).equals(UserType.INDIVIDUAL)) {
            if (ticketCount + basketRequest.getQuantity() > 5) {
                throw new BasketException(ExceptionMessages.MAX_INDIVIDUAL_TICKET);
            }
            long malePassengerCount = ticketList.stream()
                    .filter(t -> basketRequest.getPassengerGender().equals(Gender.MALE))
                    .count();
            if (basketRequest.getQuantity() + malePassengerCount > 2) {
                throw new BasketException(ExceptionMessages.MAX_INDIVIDUAL_TICKET_FOR_MAN);
            }
        } else if (jwtUtils.extractUserType(token).equals(UserType.CORPORATE)) {
            if (ticketCount + basketRequest.getQuantity() > 40) {
                throw new BasketException(ExceptionMessages.MAX_CORPORATE_TICKET);
            }
        }

        for (int i = 0; i < basketRequest.getQuantity(); i++) {
            ticketList.add(ticket);
        }

        BigDecimal totalPrice = totalPrice(ticketList);

        basketRepository.save(basket);
        BasketResponse basketResponse = BasketResponse.builder()
                .email(email)
                .totalPrice(totalPrice)
                .ticketList(basket.getTicketList())
                .build();
        LogTemplate logTemplate = new LogTemplate();
        logTemplate.setLog("email: " + email);
        logTemplate.setMessage("Basket create");
        kafkaProducer.sendLog(logTemplate);

        return GenericResponse.success(basketResponse, HttpStatus.CREATED,null);

    }

    public GenericResponse<BasketResponse> getBasket(String token) {

        String email = jwtUtils.extractEmail2(token);
        Basket basket = basketRepository.findByEmail(email);
//        Optional<List<Ticket>> ticketList = Optional.ofNullable(basketRepository.findByEmail(email).getTicketList());

        if (basket == null) {
            throw new BasketException(ExceptionMessages.TICKET_NOT_FOUND);
        }
        BigDecimal totalPrice = totalPrice(basket.getTicketList());

        BasketResponse basketResponse =BasketResponse.builder()
                .email(email)
                .ticketList(basket.getTicketList())
                .totalPrice(totalPrice).build();

        return GenericResponse.success(basketResponse, HttpStatus.OK,null);
    }

    public GenericResponse<BasketResponse> clearBasket(String token) {

        String email = jwtUtils.extractEmail(token);
        Optional<Basket> basket = Optional.ofNullable(basketRepository.findByEmail(email));

        if (basket.isEmpty()) {
            throw new BasketException(ExceptionMessages.BASKET_NOT_FOUND);
        }

        basket.get().getTicketList().clear();
        basket.get().setTotalPrice(BigDecimal.ZERO);
        basketRepository.save(basket.get());
        BasketResponse basketResponse = BasketResponse.builder()
                .email(email)
                .ticketList(basket.get().getTicketList())
                .totalPrice(totalPrice(basket.get().getTicketList()))
                .build();

        LogTemplate logTemplate = new LogTemplate();
        logTemplate.setLog("email: " + email);
        logTemplate.setMessage("Clear basket");
        kafkaProducer.sendLog(logTemplate);

        return GenericResponse.success(basketResponse,HttpStatus.OK,"Basket clear successfully");

    }

    public GenericResponse<BigDecimal> paymentToBankNumber(String token, PaymentRequestBankTransfer request){

        String email = jwtUtils.extractEmail2(token);
        Basket basket = basketRepository.findByEmail(email);

        if(basket.getTicketList().isEmpty()){
            throw new BasketException(ExceptionMessages.BASKET_NOT_FOUND);
        }

        PaymentRequestToBankAccountNumber paymentRequestToPaymentToBankNumber = PaymentRequestToBankAccountNumber.builder()
                .userEmail(email)
                .paymentMethod(request.getPaymentMethod())
                .bankAccountNumber(request.getBankAccountNumber())
                .totalPrice(totalPrice(basket.getTicketList()))
                .build();

        paymentClientService.paymentToBankAccountNumber(paymentRequestToPaymentToBankNumber);

        Notification notification = new Notification();

        notification.setNotificationTime(LocalDateTime.now());
        notification.setNotificationType(NotificationType.EMAIL);

        String message =basket.getTicketList().stream()
                .map(ticket -> String.format("TicketCode: %s, From: %s, To: %s, VehicleType: %s, DepartureDate: %s, DepartureTime: %s, TicketPrice: %.2f",
                        ticket.getTicketCode(),
                        ticket.getFromCity(),
                        ticket.getToCity(),
                        ticket.getVehicleType(),
                        ticket.getDepartureDate(),
                        ticket.getDepartureTime(),
                        ticket.getTicketPrice()))
                .collect(Collectors.joining("; "));
        notification.setNotificationMessage(message);

        notification.setNotificationUser(basket.getEmail());

        rabbitProducer.sendNotification(notification);

        return GenericResponse.success(paymentRequestToPaymentToBankNumber.getTotalPrice(),HttpStatus.OK,"Payment accepted");

    }

    public GenericResponse<BigDecimal> paymentToCreditCart(String token, PaymentRequestCreditCard request){

        String email = jwtUtils.extractEmail2(token);
        Basket basket = basketRepository.findByEmail(email);

        if(basket.getTicketList().isEmpty()){
            throw new BasketException(ExceptionMessages.BASKET_NOT_FOUND);
        }

        PaymentRequestToCreditCard paymentRequestToCreditCard = PaymentRequestToCreditCard.builder()
                .userEmail(email)
                .creditCardNumber(request.getCreditCardNumber())
                .cardExpiredDate(request.getCardExpiredDate())
                .cardCVC(request.getCardCVC())
                .totalPrice(totalPrice(basket.getTicketList()))
                .build();

        paymentClientService.paymentToCreditCard(paymentRequestToCreditCard);

        return GenericResponse.success(paymentRequestToCreditCard.getTotalPrice(),HttpStatus.OK,"Payment accepted");
    }




    private BigDecimal totalPrice(List<Ticket> ticketList) {

        BigDecimal totalPrice = ticketList.stream()
                                    .map(ticket1 -> BigDecimal.valueOf(ticket1.getTicketPrice()))
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPrice;
    }




}











