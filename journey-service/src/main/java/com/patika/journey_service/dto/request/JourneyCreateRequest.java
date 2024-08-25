package com.patika.journey_service.dto.request;

import com.patika.journey_service.model.enums.VehicleType;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@Setter
public class JourneyCreateRequest {


    private String fromCity;

    private String toCity;

    private VehicleType vehicleType;

    private LocalDate departureDate;

    private LocalTime departureTime;

    private boolean isCancelled;

    private double ticketPrice;

    private LocalDateTime createdDateTime;


}
