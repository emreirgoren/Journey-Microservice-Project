package com.patika.exception_log_service.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@Document(collection = "exceptions")
public class Exception {

    @Id
    private String id;

    private String serviceType;

    private String exceptionMessage;

    private HttpStatus exceptionStatusCode;

}
