package com.patika.basket_service.exception;

import com.patika.basket_service.dto.response.GenericResponse;
import com.patika.basket_service.producer.KafkaProducer.ExceptionTemplate;
import com.patika.basket_service.producer.KafkaProducer.KafkaProducer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    private final KafkaProducer kafkaProducer;

    public GeneralExceptionHandler(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @ExceptionHandler(BasketException.class)
    public GenericResponse handleBlogHubException(BasketException basketException){
        ExceptionTemplate exceptionTemplate = new ExceptionTemplate();
        exceptionTemplate.setExceptionMessage(basketException.getMessage());
        exceptionTemplate.setExceptionStatusCode(HttpStatus.NOT_FOUND);
        kafkaProducer.sendException(exceptionTemplate);
        return GenericResponse.failed(basketException.getMessage());
    }

}
