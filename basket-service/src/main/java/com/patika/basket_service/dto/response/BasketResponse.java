package com.patika.basket_service.dto.response;

import com.patika.basket_service.model.Ticket;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class BasketResponse {

    private String email;

    private BigDecimal totalPrice;

    private List<Ticket> ticketList = new ArrayList<>();

}
