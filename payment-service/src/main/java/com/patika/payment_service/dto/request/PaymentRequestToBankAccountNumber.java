package com.patika.payment_service.dto.request;

import com.patika.payment_service.model.enums.PaymentMethod;
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
