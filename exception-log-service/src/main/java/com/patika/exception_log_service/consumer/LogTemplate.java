package com.patika.exception_log_service.consumer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogTemplate {

    private String serviceType;
    private String log;
    private String message;


}
