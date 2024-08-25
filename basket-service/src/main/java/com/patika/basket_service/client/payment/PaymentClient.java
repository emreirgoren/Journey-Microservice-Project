package com.patika.basket_service.client.payment;

import com.patika.basket_service.dto.request.PaymentRequestToBankAccountNumber;
import com.patika.basket_service.dto.request.PaymentRequestToCreditCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "payment-service",url = "localhost:9016/api/v1/payments")
public interface PaymentClient {

    @PostMapping("/paymentToBankAccountNumber")
    void paymentToBankAccountNumber(@RequestBody PaymentRequestToBankAccountNumber paymentRequestToPaymentToBankNumber);

    @PostMapping("/paymentToCreditCard")
    void paymentToCreditCard(@RequestBody PaymentRequestToCreditCard paymentRequestToCreditCard);
}
