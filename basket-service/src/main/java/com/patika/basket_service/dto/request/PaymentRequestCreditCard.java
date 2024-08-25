package com.patika.basket_service.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestCreditCard {

    private PaymentMethod paymentMethod;

    private String creditCardNumber;
    private String cardExpiredDate;
    private String cardCVC;
}
