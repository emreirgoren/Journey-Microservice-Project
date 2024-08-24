package com.patika.exception_log_service.consumer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class ExceptionTemplate implements Serializable {

    private String serviceType;

    private String exceptionMessage;

    private HttpStatus exceptionStatusCode;

}
