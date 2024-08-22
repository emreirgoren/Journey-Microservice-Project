package com.patika.notification_service.strategy;

import com.patika.notification_service.model.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationContext {

    private NotificationStrategy notificationStrategy;

    public NotificationContext(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public void setNotificationStrategy(NotificationStrategy notificationStrategy){
        this.notificationStrategy=notificationStrategy;
    }

    public void executeStrategy(Notification notification){
        notificationStrategy.send(notification);
    }

}
