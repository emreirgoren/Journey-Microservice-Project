package com.patika.notification_service.convert;

import com.patika.notification_service.model.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter {

    public static String notificationToPushNotification(Notification notification){


        String pushNotificatiom ="Notification User: "+  notification.getNotificationUser().toString() +
                                    " Notification message: " + notification.getNotificationMessage() ;

        return pushNotificatiom;
    }

}
