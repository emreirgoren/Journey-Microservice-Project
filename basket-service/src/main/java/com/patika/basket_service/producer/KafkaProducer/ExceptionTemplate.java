package com.patika.basket_service.producer.KafkaProducer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class ExceptionTemplate implements Serializable {

    private String serviceType ="basket-service";

    private String exceptionMessage;

    private HttpStatus exceptionStatusCode;

}
