package com.patika.ticket_search_service.producer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class ExceptionTemplate implements Serializable {

    private String serviceType ="ticket-search-service";

    private String exceptionMessage;

    private HttpStatus exceptionStatusCode;

    public static ExceptionTemplate exceptionTemplate(String exceptionMessage, HttpStatus exceptionStatusCode){
        ExceptionTemplate exceptionTemplate = new ExceptionTemplate();
        exceptionTemplate.setExceptionMessage(exceptionMessage);
        exceptionTemplate.setExceptionStatusCode(exceptionStatusCode);
        return exceptionTemplate;
    }

}
