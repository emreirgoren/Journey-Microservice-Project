package com.patika.basket_service.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class PaymentRequestToCreditCard {

    private String userEmail;

    private PaymentMethod paymentMethod;

    private String creditCardNumber;
    private String cardExpiredDate;
    private String cardCVC;

    private BigDecimal totalPrice;

}
