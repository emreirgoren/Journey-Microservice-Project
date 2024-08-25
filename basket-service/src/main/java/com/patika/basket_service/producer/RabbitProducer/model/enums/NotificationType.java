package com.patika.basket_service.producer.RabbitProducer.model.enums;

public enum NotificationType {

    SMS("SMS"),
    EMAIL("EMAIL"),
    PUSH("PUSH");

    private String message;

    NotificationType(String message) {
        this.message = message;
    }
}
