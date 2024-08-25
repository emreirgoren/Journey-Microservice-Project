package com.patika.journey_index_service.model.document;

import com.patika.journey_index_service.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "journeys")
public class JourneyDocument {

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
