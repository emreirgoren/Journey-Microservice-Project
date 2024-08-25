package com.patika.journey_index_service.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.patika.journey_index_service.model.enums.VehicleType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Journey {

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
