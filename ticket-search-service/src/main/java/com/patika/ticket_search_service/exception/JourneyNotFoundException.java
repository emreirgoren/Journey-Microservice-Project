package com.patika.ticket_search_service.exception;

public class JourneyNotFoundException extends RuntimeException {

    public JourneyNotFoundException(String message) {
        super(message);
    }
}
