package com.patika.ticket_search_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patika.ticket_search_service.model.enums.VehicleType;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "journeys")
public class Journey {

    @Id
    private Long id;

    private String ticketCode;

    private String fromCity;

    private String toCity;

    private VehicleType vehicleType;

    //@Field(type = FieldType.Date,format = DateFormat.date)
    private String departureDate;

    //@Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private String departureTime;

    private int capacity;

    private boolean isCancelled;

    private double ticketPrice;

    //@Field(type = FieldType.Date,format = DateFormat.date_hour_minute_second_millis)
    private String createdDateTime;

    private int availableTicket;

}
