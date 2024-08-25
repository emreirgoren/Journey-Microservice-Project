package com.patika.payment_service.service;

import com.patika.payment_service.dto.request.PaymentRequestToBankAccountNumber;
import com.patika.payment_service.dto.request.PaymentRequestToCreditCard;

public interface PaymentService {

    void paymentToBankAccountNumber(PaymentRequestToBankAccountNumber request);

    void paymentToCreditCard(PaymentRequestToCreditCard request);
}
