package com.patika.basket_service.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PaymentRequestToBankAccountNumber {

    private String userEmail;

    private PaymentMethod paymentMethod;

    private String bankAccountNumber;

    private BigDecimal totalPrice;

}
