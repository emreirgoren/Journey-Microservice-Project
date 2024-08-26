package com.patika.journey_service.service;


import com.patika.journey_service.dto.request.JourneyCancelledRequest;
import com.patika.journey_service.dto.request.JourneyCreateRequest;
import com.patika.journey_service.dto.response.GenericResponse.GenericResponse;
import com.patika.journey_service.dto.response.JourneyCancelledResponse;
import com.patika.journey_service.dto.response.JourneyCreateResponse;
import com.patika.journey_service.dto.response.SalesReport;
import com.patika.journey_service.model.Journey;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface JourneyService {


    GenericResponse<JourneyCreateResponse> createJourney(JourneyCreateRequest request);

    ResponseEntity<GenericResponse<JourneyCancelledResponse>> cancelledJourney(JourneyCancelledRequest request);

    List<Journey> getJourneyList();

    List<SalesReport> getSalesReport();

    Journey getJourney(String ticketCode);

}
