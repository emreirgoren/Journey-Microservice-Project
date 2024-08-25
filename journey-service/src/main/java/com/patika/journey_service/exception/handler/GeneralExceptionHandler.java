package com.patika.journey_service.exception.handler;

import com.patika.journey_service.dto.response.ExceptionResponse;
import com.patika.journey_service.dto.response.GenericResponse.GenericResponseConstants.GenericResponseFailedConstants;
import com.patika.journey_service.exception.JourneyAlreadyCancelledException;
import com.patika.journey_service.exception.JourneyNotFoundException;
import com.patika.journey_service.exception.LimitExceededJourneyException;
import com.patika.journey_service.producer.ExceptionTemplate;
import com.patika.journey_service.producer.KafkaProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    private KafkaProducer kafkaProducer;

    public GeneralExceptionHandler(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @ExceptionHandler(JourneyNotFoundException.class)
    public ResponseEntity<ExceptionResponse> journeyNotFoundException(JourneyNotFoundException exception){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                        .exceptionMessage(exception.getMessage())
                        .exceptionStatus(GenericResponseFailedConstants.JOURNEY_NOT_FOUND)
                        .exceptionStatusCode(HttpStatus.NOT_FOUND)
                        .build();

        ExceptionTemplate exceptionTemplate = new ExceptionTemplate();
        exceptionTemplate.setServiceType("journey-service");
        exceptionTemplate.setExceptionMessage(exception.getMessage());
        exceptionTemplate.setExceptionStatusCode(HttpStatus.NOT_FOUND);
        kafkaProducer.sendException(exceptionTemplate);

        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JourneyAlreadyCancelledException.class)
    public ResponseEntity<ExceptionResponse> journeyAlreadyCancelledException(JourneyAlreadyCancelledException exception){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionMessage(exception.getMessage())
                .exceptionStatus(GenericResponseFailedConstants.ALREADY_CANCELLED)
                .exceptionStatusCode(HttpStatus.CONFLICT)
                .build();

        ExceptionTemplate exceptionTemplate = new ExceptionTemplate();
        exceptionTemplate.setExceptionMessage(exception.getMessage());
        exceptionTemplate.setExceptionStatusCode(HttpStatus.CONFLICT);
        kafkaProducer.sendException(exceptionTemplate);

        return new ResponseEntity<>(exceptionResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LimitExceededJourneyException.class)
    public ResponseEntity<ExceptionResponse> limitExceededJourneyException(LimitExceededJourneyException exception){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionMessage(exception.getMessage())
                .exceptionStatus(GenericResponseFailedConstants.LIMIT_EXCEEDED_JOURNEY)
                .exceptionStatusCode(HttpStatus.CONFLICT)
                .build();

        ExceptionTemplate exceptionTemplate = new ExceptionTemplate();
        exceptionTemplate.setExceptionMessage(exception.getMessage());
        exceptionTemplate.setExceptionStatusCode(HttpStatus.CONFLICT);
        kafkaProducer.sendException(exceptionTemplate);

        return new ResponseEntity<>(exceptionResponse,HttpStatus.CONFLICT);
    }



}
