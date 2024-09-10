package com.patika.journey_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patika.journey_service.dto.request.JourneyCreateRequest;
import com.patika.journey_service.dto.response.GenericResponse.GenericResponse;
import com.patika.journey_service.dto.response.JourneyCreateResponse;
import com.patika.journey_service.producer.KafkaProducer;
import com.patika.journey_service.service.JourneyService;
import com.patika.journey_service.service.impl.JourneyServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JourneyController.class)
public class JourneyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JourneyService journeyService;

    @MockBean
    private GenericResponse genericResponse;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private KafkaProducer kafkaProducer;


    @Test
    void testCreateJourney() throws Exception {

        //given
        JourneyCreateRequest journeyCreateRequest = new JourneyCreateRequest();
        journeyCreateRequest.setFromCity("Elazig");
        journeyCreateRequest.setToCity("Malatya");
        JourneyCreateResponse journeyCreateResponse = new JourneyCreateResponse();
        journeyCreateResponse.setFromCity("Elazig");
        journeyCreateResponse.setToCity("Malatya");

        Mockito.when(journeyService.createJourney(Mockito.any(JourneyCreateRequest.class)))
                .thenReturn(GenericResponse.success("Test",HttpStatus.CREATED,journeyCreateResponse));

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/journeys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(journeyCreateRequest)));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Test"))
                .andExpect(jsonPath("$.httpStatus").value("CREATED"))
                .andExpect(jsonPath("$.data.fromCity").value("Elazig"))
                .andExpect(jsonPath("$.data.toCity").value("Malatya"));


        Mockito.verify(journeyService,Mockito.times(1)).createJourney(Mockito.any(JourneyCreateRequest.class));

    }
    

}

