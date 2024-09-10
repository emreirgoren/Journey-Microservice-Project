package com.patika.journey_service.service;

import com.patika.journey_service.dto.request.JourneyCreateRequest;
import com.patika.journey_service.dto.response.GenericResponse.GenericResponse;
import com.patika.journey_service.dto.response.GenericResponse.GenericResponseConstants.GenericResponseSuccessConstants;
import com.patika.journey_service.dto.response.JourneyCreateResponse;
import com.patika.journey_service.mapper.JourneyMapper;
import com.patika.journey_service.model.Journey;
import com.patika.journey_service.model.enums.VehicleType;
import com.patika.journey_service.producer.KafkaProducer;
import com.patika.journey_service.producer.LogTemplate;
import com.patika.journey_service.repository.JourneyJpaRepository;
import com.patika.journey_service.service.businessRules.JourneyRules;
import com.patika.journey_service.service.impl.JourneyServiceImpl;
import com.patika.journey_service.util.GenerateTicketCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JourneyServiceTest {

    @InjectMocks
    private JourneyServiceImpl journeyServiceImpl;
    @Mock
    private JourneyRules journeyRules;
    @Mock
    private JourneyJpaRepository journeyJpaRepository;
    @Mock
    private KafkaProducer kafkaProducer;
    @Mock
    private JourneyMapper journeyMapper;
    @Mock
    private GenerateTicketCode generateTicketCode;

    @Test
    void testCreateJourney()throws Exception {

        JourneyCreateRequest request = request();
        Journey journey = new Journey();
        journey.setTicketCode("UNIQUE_CODE");
        journey.setFromCity(request.getFromCity());
        journey.setToCity(request.getToCity());

        Mockito.when(generateTicketCode.createUniqueTicketCode()).thenReturn("UNIQUE_CODE");
        Mockito.when(journeyJpaRepository.save(Mockito.any(Journey.class))).thenReturn(journey);

        // when
        GenericResponse<JourneyCreateResponse> response = journeyServiceImpl.createJourney(request);

        // then
        Mockito.verify(journeyRules, Mockito.times(1)).limitExceededJourneyRule(request);
        Mockito.verify(journeyJpaRepository, Mockito.times(1)).save(Mockito.any(Journey.class));
        Mockito.verify(kafkaProducer, Mockito.times(1)).sendJourney(Mockito.any(Journey.class));
        Mockito.verify(kafkaProducer, Mockito.times(1)).sendLog(Mockito.any(LogTemplate.class));

        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
        assertEquals("UNIQUE_CODE", response.getData().getTicketCode());
        assertEquals(GenericResponseSuccessConstants.JOURNEY_CREATED, response.getMessage());
    }



    private JourneyCreateRequest request(){
        JourneyCreateRequest journeyCreateRequest = new JourneyCreateRequest();

        journeyCreateRequest.setFromCity("İstanbul");
        journeyCreateRequest.setToCity("Ankara");
        journeyCreateRequest.setVehicleType(VehicleType.BUS);
        return journeyCreateRequest;

    }

}
