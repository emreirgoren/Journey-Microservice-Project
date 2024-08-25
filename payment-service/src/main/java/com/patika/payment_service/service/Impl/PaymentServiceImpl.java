package com.patika.payment_service.service.Impl;

import com.patika.payment_service.client.service.UserClientService;
import com.patika.payment_service.dto.request.PaymentRequestToBankAccountNumber;
import com.patika.payment_service.dto.request.PaymentRequestToCreditCard;
import com.patika.payment_service.model.Payment;
import com.patika.payment_service.repository.PaymentRepository;
import com.patika.payment_service.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserClientService userClientService;
    public PaymentServiceImpl(PaymentRepository paymentRepository, UserClientService userClientService) {
        this.paymentRepository = paymentRepository;
        this.userClientService = userClientService;
    }

    @Override
    public void paymentToBankAccountNumber(PaymentRequestToBankAccountNumber request) {

        if(request.getUserEmail().isEmpty() && request.getTotalPrice().equals(0) &&request.getBankAccountNumber().isEmpty()){
            throw new RuntimeException();
        }
        //request.getPaymentMethod();
        String bankAccountNumber = request.getBankAccountNumber();

        Payment payment = Payment.builder()
                .userId(userClientService.getUserIdByEmail(request.getUserEmail()))
                .amount(request.getTotalPrice())
                .build();

        paymentRepository.save(payment);
    }

    @Override
    public void paymentToCreditCard(PaymentRequestToCreditCard request) {

        if (request.getUserEmail().isEmpty() && request.getTotalPrice().equals(0) && request.getCreditCardNumber().isEmpty() &&
        request.getCardExpiredDate().isEmpty() && request.getCardCVC().isEmpty()){
            throw new RuntimeException();
        }

        String creditCardNumber = request.getCreditCardNumber();
        String cardExpiredDate = request.getCardExpiredDate();
        String cardCVC = request.getCardCVC();

        Payment payment = Payment.builder()
                .userId(userClientService.getUserIdByEmail(request.getUserEmail()))
                .amount(request.getTotalPrice())
                .build();

        paymentRepository.save(payment);

    }
}
