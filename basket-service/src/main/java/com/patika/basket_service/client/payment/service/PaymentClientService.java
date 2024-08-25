package com.patika.basket_service.client.payment.service;

import com.patika.basket_service.client.payment.PaymentClient;
import com.patika.basket_service.dto.request.PaymentRequestToBankAccountNumber;
import com.patika.basket_service.dto.request.PaymentRequestToCreditCard;
import org.springframework.stereotype.Service;

@Service
public class PaymentClientService {

    private final PaymentClient paymentClient;

    public PaymentClientService(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }


    public void paymentToBankAccountNumber(PaymentRequestToBankAccountNumber paymentRequestToPaymentToBankNumber) {
        paymentClient.paymentToBankAccountNumber(paymentRequestToPaymentToBankNumber);
    }

    public void paymentToCreditCard(PaymentRequestToCreditCard paymentRequestToCreditCard) {
        paymentClient.paymentToCreditCard(paymentRequestToCreditCard);
    }
}
