package com.patika.notification_service.service;

import com.patika.notification_service.model.Notification;
import com.patika.notification_service.model.enums.NotificationType;
import com.patika.notification_service.producer.RabbitMQProducer;
import com.patika.notification_service.repository.NotificationRepository;
import com.patika.notification_service.strategy.*;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationContext notificationContext;
    private final RabbitMQProducer rabbitMQProducer;

    private final SmsNotificationStrategy smsNotificationStrategy;
    private final EmailNotificationStrategy emailNotificationStrategy;
    private final PushNotificationStrategy pushNotificationStrategy;

    public NotificationService(NotificationRepository notificationRepository, NotificationContext notificationContext, RabbitMQProducer rabbitMQProducer, SmsNotificationStrategy smsNotificationStrategy, EmailNotificationStrategy emailNotificationStrategy, PushNotificationStrategy pushNotificationStrategy) {
        this.notificationRepository = notificationRepository;
        this.notificationContext = notificationContext;
        this.rabbitMQProducer = rabbitMQProducer;
        this.smsNotificationStrategy = smsNotificationStrategy;
        this.emailNotificationStrategy = emailNotificationStrategy;
        this.pushNotificationStrategy = pushNotificationStrategy;
    }

    public void sendNotification(Notification notification) {

        notificationRepository.save(notification);

        if(notification.getNotificationType().equals(NotificationType.SMS)){
            notificationContext.setNotificationStrategy(smsNotificationStrategy);
        } else if (notification.getNotificationType().equals(NotificationType.EMAIL)) {
            notificationContext.setNotificationStrategy(emailNotificationStrategy);
        } else if (notification.getNotificationType().equals(NotificationType.PUSH)) {
            notificationContext.setNotificationStrategy(pushNotificationStrategy);
        }

        notificationContext.executeStrategy(notification);
    }
}
