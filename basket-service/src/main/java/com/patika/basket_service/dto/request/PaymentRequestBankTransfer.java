package com.patika.basket_service.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestBankTransfer {

    private PaymentMethod paymentMethod;

    private String bankAccountNumber;


}
