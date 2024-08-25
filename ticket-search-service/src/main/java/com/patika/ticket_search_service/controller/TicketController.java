package com.patika.ticket_search_service.controller;

import com.patika.ticket_search_service.dto.request.JourneySearchByCityAndDepartureDateRequest;
import com.patika.ticket_search_service.dto.request.JourneySearchByCityAndVehicleTypeRequest;
import com.patika.ticket_search_service.dto.request.JourneySearchByCityRequest;
import com.patika.ticket_search_service.dto.response.JourneySearchResponse;
import com.patika.ticket_search_service.model.Journey;
import com.patika.ticket_search_service.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/searches")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/searchByCity")
    public List<JourneySearchResponse> getTicketListByCity(@RequestBody JourneySearchByCityRequest journeySearchByCityRequest) {
        return ticketService.getTicketListByCity(journeySearchByCityRequest);
    }

    @GetMapping("/searchByCityAndVehicleType")
    public List<JourneySearchResponse> getTicketListByCityAndVehicleType(@RequestBody JourneySearchByCityAndVehicleTypeRequest request) {
        return ticketService.getTicketListByCityAndVehicleType(request);
    }

    @GetMapping("/searchByCityAndDepartureDate")
    public List<JourneySearchResponse> getTicketListByCityAndDepartureDate(@RequestBody JourneySearchByCityAndDepartureDateRequest request) {
        return ticketService.getTicketListByCityAndDepartureDate(request);
    }

    @GetMapping("/{ticketCode}")
    public Journey getTicket(@PathVariable String ticketCode) {
        return ticketService.getTicketByTicketCode(ticketCode);
    }


}
