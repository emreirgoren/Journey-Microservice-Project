package com.patika.basket_service.producer.KafkaProducer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogTemplate {

    private String serviceType ="basket-service";
    private String log;
    private String message;

}
