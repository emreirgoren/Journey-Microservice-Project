package com.patika.payment_service.controller;

import com.patika.payment_service.dto.request.PaymentRequestToBankAccountNumber;
import com.patika.payment_service.dto.request.PaymentRequestToCreditCard;
import com.patika.payment_service.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/paymentToBankAccountNumber")
    public void paymentToBankAccountNumber(@RequestBody PaymentRequestToBankAccountNumber request) {
        paymentService.paymentToBankAccountNumber(request);
    }

    @PostMapping("/paymentToCreditCard")
    public void paymentToCreditCard(@RequestBody PaymentRequestToCreditCard request) {
        paymentService.paymentToCreditCard(request);
    }
}
