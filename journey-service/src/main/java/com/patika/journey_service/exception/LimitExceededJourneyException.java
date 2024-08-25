package com.patika.journey_service.exception;

public class LimitExceededJourneyException extends RuntimeException{

    public LimitExceededJourneyException(String message) {
        super(message);
    }

}
