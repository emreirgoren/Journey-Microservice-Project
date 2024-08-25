package com.patika.journey_service.producer;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class ExceptionTemplate implements Serializable {

    private String serviceType ="journey-service";

    private String exceptionMessage;

    private HttpStatus exceptionStatusCode;

}
