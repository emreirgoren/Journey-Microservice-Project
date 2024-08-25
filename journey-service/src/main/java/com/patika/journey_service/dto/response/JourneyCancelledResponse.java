package com.patika.journey_service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patika.journey_service.model.enums.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class JourneyCancelledResponse {

    private String ticketCode;
    private String fromCity;
    private String toCity;
    private VehicleType vehicleType;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private int capacity;
    private boolean isCancelled;
    private double ticketPrice;
    private LocalDateTime createdDateTime;

}
