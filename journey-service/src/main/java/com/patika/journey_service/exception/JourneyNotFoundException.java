package com.patika.journey_service.exception;

public class JourneyNotFoundException extends RuntimeException{

    public JourneyNotFoundException(String message) {
        super(message);
    }
}
