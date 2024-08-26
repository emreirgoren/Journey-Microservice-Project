package com.patika.journey_service.controller;


import com.patika.journey_service.dto.request.JourneyCancelledRequest;
import com.patika.journey_service.dto.request.JourneyCreateRequest;
import com.patika.journey_service.dto.response.GenericResponse.GenericResponse;
import com.patika.journey_service.dto.response.JourneyCancelledResponse;
import com.patika.journey_service.dto.response.JourneyCreateResponse;
import com.patika.journey_service.dto.response.SalesReport;
import com.patika.journey_service.model.Journey;
import com.patika.journey_service.service.JourneyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/journeys")
public class JourneyController {

    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    /**
     * Create Journey
     */

    @PostMapping
    public GenericResponse<JourneyCreateResponse> createJourney(@RequestBody JourneyCreateRequest request) {
        return journeyService.createJourney(request);
    }

    /**
     * Cancelled
     */
    @PostMapping("/cancelled")
    public ResponseEntity<GenericResponse<JourneyCancelledResponse>> cancelledJourney(@RequestBody JourneyCancelledRequest request) {
        return journeyService.cancelledJourney(request);
    }

    @GetMapping
    public List<Journey> getList() {
        return journeyService.getJourneyList();
    }

    @GetMapping("/getSalesReport")
    public List<SalesReport> getSalesRepost() {
        return journeyService.getSalesReport();
    }

    @GetMapping("/")
    public Journey getJourney(String ticketCode){
        return journeyService.getJourney(ticketCode);
    }

}
