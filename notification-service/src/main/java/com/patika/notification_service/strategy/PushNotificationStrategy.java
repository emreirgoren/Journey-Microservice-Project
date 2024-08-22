package com.patika.notification_service.strategy;

import com.patika.notification_service.convert.NotificationConverter;
import com.patika.notification_service.model.Notification;
import com.patika.notification_service.producer.RabbitMQProducer;import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class PushNotificationStrategy implements NotificationStrategy {

    private RabbitMQProducer rabbitMQProducer;

    public PushNotificationStrategy(RabbitMQProducer rabbitMQProducer) {

        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public void send(Notification notification) {

        rabbitMQProducer.sendPushNotification(NotificationConverter.notificationToPushNotification(notification));


    }


}
