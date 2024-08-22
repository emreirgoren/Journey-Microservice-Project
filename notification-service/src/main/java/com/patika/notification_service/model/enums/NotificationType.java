package com.patika.notification_service.model.enums;

public enum NotificationType {

    SMS("SMS"),
    EMAIL("EMAIL"),
    PUSH("PUSH");

    private String message;

    NotificationType(String message) {
        this.message = message;
    }
}
