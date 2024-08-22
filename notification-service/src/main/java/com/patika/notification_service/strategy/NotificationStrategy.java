package com.patika.notification_service.strategy;

import com.patika.notification_service.model.Notification;

public interface NotificationStrategy {

    void send(Notification notification);
}
