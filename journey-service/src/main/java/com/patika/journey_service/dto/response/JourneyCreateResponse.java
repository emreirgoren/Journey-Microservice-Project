package com.patika.journey_service.dto.response;

import com.patika.journey_service.model.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class JourneyCreateResponse {


    private String ticketCode;

    private String fromCity;

    private String toCity;

    private VehicleType vehicleType;

    private LocalDate departureDate;

    private LocalTime departureTime;

    private int capacity;

    private boolean isCancelled;

    private double ticketPrice;

    private int availableTicket;

}
