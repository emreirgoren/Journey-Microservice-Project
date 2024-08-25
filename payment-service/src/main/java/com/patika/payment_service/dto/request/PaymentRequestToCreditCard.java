package com.patika.payment_service.dto.request;

import com.patika.payment_service.model.enums.PaymentMethod;
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
