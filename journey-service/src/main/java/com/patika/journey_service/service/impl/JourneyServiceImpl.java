package com.patika.journey_service.service.impl;

import com.patika.journey_service.dto.request.JourneyCancelledRequest;
import com.patika.journey_service.dto.request.JourneyCreateRequest;
import com.patika.journey_service.dto.response.GenericResponse.GenericResponse;
import com.patika.journey_service.dto.response.GenericResponse.GenericResponseConstants.GenericResponseSuccessConstants;
import com.patika.journey_service.dto.response.JourneyCancelledResponse;
import com.patika.journey_service.dto.response.JourneyCreateResponse;
import com.patika.journey_service.dto.response.SalesReport;
import com.patika.journey_service.exception.JourneyNotFoundException;
import com.patika.journey_service.mapper.JourneyMapper;
import com.patika.journey_service.model.Journey;
import com.patika.journey_service.producer.KafkaProducer;
import com.patika.journey_service.producer.LogTemplate;
import com.patika.journey_service.repository.JourneyJpaRepository;

import com.patika.journey_service.service.JourneyService;
import com.patika.journey_service.service.businessRules.JourneyRules;
import com.patika.journey_service.util.GenerateTicketCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JourneyServiceImpl implements JourneyService {

    private JourneyJpaRepository journeyJpaRepository;
    private GenerateTicketCode generateTicketCode;
    private JourneyRules journeyRules;
    private KafkaProducer kafkaProducer;


    public JourneyServiceImpl(JourneyJpaRepository journeyJpaRepository, GenerateTicketCode generateTicketCode, JourneyRules journeyRules, KafkaProducer kafkaProducer) {
        this.journeyJpaRepository = journeyJpaRepository;
        this.generateTicketCode = generateTicketCode;
        this.journeyRules = journeyRules;
        this.kafkaProducer = kafkaProducer;


    }

    /**
     * Create Journey
     */
    @CacheEvict(cacheNames = "journeys", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackForClassName = {"JourneyNotFoundException.class"}
            , rollbackFor = SQLException.class)
    @Override
    public GenericResponse<JourneyCreateResponse> createJourney(JourneyCreateRequest request) {

        journeyRules.limitExceededJourneyRule(request);

        Journey journey = JourneyMapper.journeyCreateRequestToJourney(request);
        journey.setTicketCode(generateTicketCode.createUniqueTicketCode());
        journey.setCreatedDateTime(LocalDateTime.now());
        journey.setCancelled(false);

        journeyJpaRepository.save(journey);
        kafkaProducer.sendJourney(journey);

        JourneyCreateResponse journeyCreateResponse = JourneyMapper.journeyToJourneyCreateResponse(journey);
        LogTemplate logTemplate=new LogTemplate();
        logTemplate.setLog("Sefer oluşturuldu.");
        logTemplate.setMessage(journeyCreateResponse.getTicketCode());
        kafkaProducer.sendLog(logTemplate);

        return GenericResponse.success(
                GenericResponseSuccessConstants.JOURNEY_CREATED,
                HttpStatus.CREATED,
                journeyCreateResponse);
    }

    /**
     * Cancelled Journey
     */
    @Override
    public ResponseEntity<GenericResponse<JourneyCancelledResponse>> cancelledJourney(JourneyCancelledRequest request) {

        journeyRules.journeyNotFound(request);

        Optional<Journey> optionalJourney = journeyJpaRepository.findByTicketCode(request.getTicketCode());
        Journey foundJourney = optionalJourney.get();
        journeyRules.journeyIsCancelled(foundJourney);

        foundJourney.setCancelled(true);
        journeyJpaRepository.save(foundJourney);

        JourneyCancelledResponse journeyCancelledResponse = JourneyMapper.journeyToJourneyCancelledResponse(foundJourney);

        LogTemplate logTemplate=new LogTemplate();
        logTemplate.setLog("Sefer İptal edildi.");
        logTemplate.setMessage(journeyCancelledResponse.getTicketCode());
        kafkaProducer.sendLog(logTemplate);

        return new ResponseEntity<>(GenericResponse.success(
                GenericResponseSuccessConstants.JOURNEY_CANCELLED,
                HttpStatus.OK,
                journeyCancelledResponse), HttpStatus.OK);
    }

    /**
     * Journey List
     */
    @Cacheable(value = "journeys", cacheNames = "journeys")
    @Transactional(readOnly = true)
    @Override
    public List<Journey> getJourneyList() {
        List<Journey> listResponseEntity = journeyJpaRepository.findAll();
        log.info("bloglar dbden getirildi");
        return listResponseEntity;
    }

    /**
     * Sales Report
     */
    @Override
    public List<SalesReport> getSalesReport() {
        List<Journey> journeyList = journeyJpaRepository.findAll();

        return journeyList.stream()
                .collect(Collectors.groupingBy(
                        Journey::getTicketCode,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                journeys -> {
                                    Long totalTicketsSold = (long) journeys.stream()
                                            .mapToInt(journey -> journey.getCapacity() - journey.getAvailableTicket())
                                            .sum();
                                    double totalPrice = journeys.stream()
                                            .mapToDouble(journey -> (journey.getCapacity() - journey.getAvailableTicket()) * journey.getTicketPrice())
                                            .sum();
                                    return new SalesReport(
                                            journeys.get(0).getTicketCode(),
                                            totalTicketsSold,
                                            totalPrice
                                    );
                                }
                        )
                ))
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    @Cacheable(value = "journeys", cacheNames = "journeys")
    @Transactional(readOnly = true)
    @Override
    public Journey getJourney(String ticketCode) {

        Optional<Journey> optionalJourney = journeyJpaRepository.findByTicketCode(ticketCode);
        if (optionalJourney.isEmpty()){
            throw new JourneyNotFoundException("Sefer Bulunamadı");
        }
        return journeyJpaRepository.findByTicketCode(ticketCode).get();
    }


}
