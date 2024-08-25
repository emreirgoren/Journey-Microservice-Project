package com.patika.journey_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesReport {

    private String ticketCode;

    private Long totalTicketsSold;

    private double totalPrice;

}
