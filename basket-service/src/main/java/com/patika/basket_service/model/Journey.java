package com.patika.basket_service.model;



import com.patika.basket_service.model.enums.VehicleType;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class Journey { //sefer bilgisi

    private Long id;

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

    private int availableTicket;


}
