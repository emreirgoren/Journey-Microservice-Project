package com.patika.ticket_search_service.exception.handler;

import com.patika.ticket_search_service.dto.response.ExceptionResponse;
import com.patika.ticket_search_service.dto.response.GenericResponseConstants.GenericResponseFailedConstants;
import com.patika.ticket_search_service.exception.JourneyNotFoundException;
import com.patika.ticket_search_service.producer.ExceptionTemplate;
import com.patika.ticket_search_service.producer.KafkaProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    private final KafkaProducer kafkaProducer;

    public GeneralExceptionHandler(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @ExceptionHandler(JourneyNotFoundException.class)
    public ResponseEntity<ExceptionResponse> journeyNotFoundException(JourneyNotFoundException exception) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionMessage(exception.getMessage())
                .exceptionStatus(GenericResponseFailedConstants.JOURNEY_NOT_FOUND)
                .exceptionStatusCode(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
