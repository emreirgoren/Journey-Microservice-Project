package com.patika.exception_log_service.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "logs")
public class Log {

    @Id
    private String id;
    private String log;
    private String serviceType;
    private String message;

}
