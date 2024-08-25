package com.patika.ticket_search_service.dto.response;

import com.patika.ticket_search_service.model.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
public class JourneySearchResponse {

    private String ticketCode;

    private String fromCity;

    private String toCity;

    private VehicleType vehicleType;

    private String departureDate;

    private String departureTime;

    private boolean isCancelled;

    private double ticketPrice;

    private int availableTicket;


}
