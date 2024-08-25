package com.patika.basket_service.producer.RabbitProducer.model;

import com.patika.basket_service.producer.RabbitProducer.model.enums.NotificationType;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Notification {

    private Long id;

    private NotificationType notificationType;

    private String notificationUser;

    private String notificationMessage;

    private LocalDateTime notificationTime;

}
