package com.patika.basket_service.dto.request;

import com.patika.basket_service.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketRequest {

    private String ticketCode;

    private Gender passengerGender;

    private Integer quantity;

}
