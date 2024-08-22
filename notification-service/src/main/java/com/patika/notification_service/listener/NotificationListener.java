package com.patika.notification_service.listener;

import com.patika.notification_service.model.Notification;
import com.patika.notification_service.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationListener {

    private final NotificationService notificationService;

    public NotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queuesToDeclare =@Queue("${rabbitmq.notification.queue}"))
    public void notificationListener(Notification notification){
        try {
            notificationService.sendNotification(notification);

        }catch (Exception e){
            log.info("Kuyruk bos.");
        }
    }

}

