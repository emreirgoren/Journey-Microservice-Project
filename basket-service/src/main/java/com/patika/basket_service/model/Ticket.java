package com.patika.basket_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patika.basket_service.model.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Entity
public class Ticket {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    private String ticketCode;
    private String fromCity;
    private String toCity;

    private VehicleType vehicleType;

    private LocalDate departureDate;
    private LocalTime departureTime;
    private double ticketPrice;

}
