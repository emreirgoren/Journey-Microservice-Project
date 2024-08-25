package com.patika.basket_service.controller;

import com.patika.basket_service.dto.request.BasketRequest;
import com.patika.basket_service.dto.request.PaymentRequestBankTransfer;
import com.patika.basket_service.dto.request.PaymentRequestCreditCard;
import com.patika.basket_service.dto.response.BasketResponse;
import com.patika.basket_service.dto.response.GenericResponse;

import com.patika.basket_service.service.Impl.BasketServiceImpl;
import com.patika.basket_service.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/baskets")
public class BasketController {

    private final BasketServiceImpl basketServiceImpl;
    private final JwtUtils jwtUtils;

    public BasketController(BasketServiceImpl basketServiceImpl, JwtUtils jwtUtils) {
        this.basketServiceImpl = basketServiceImpl;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public GenericResponse<BasketResponse> addToBasket(@RequestHeader("Authorization") String token, @RequestBody BasketRequest basketRequest){

        return GenericResponse.success(basketServiceImpl.addToBasket(basketRequest,token).getData(),HttpStatus.CREATED,null);
    }

    @GetMapping
    public GenericResponse<BasketResponse> getBasket(@RequestHeader("Authorization") String token){
        return basketServiceImpl.getBasket(token);
    }

    @DeleteMapping("/clear")
    public GenericResponse<BasketResponse> clearBasket(@RequestHeader("Authorization") String token){
        return basketServiceImpl.clearBasket(token.substring(7));
    }

    @PostMapping("/paymentToBankAccountNumber")
    public GenericResponse<BigDecimal> paymentToBankAccountNumber(@RequestHeader("Authorization") String token, @RequestBody PaymentRequestBankTransfer request){
        return basketServiceImpl.paymentToBankNumber(token,request);
    }


    @PostMapping("/paymentToCreditCard")
    public GenericResponse<BigDecimal> paymentToCreditCard(@RequestHeader("Authorization") String token, @RequestBody PaymentRequestCreditCard request){
        return basketServiceImpl.paymentToCreditCart(token,request);
    }



}
