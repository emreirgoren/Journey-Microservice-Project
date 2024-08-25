package com.patika.journey_service.producer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogTemplate {

    private String serviceType ="journey-service";
    private String log;
    private String message;

}
