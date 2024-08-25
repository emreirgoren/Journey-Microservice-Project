package com.patika.auth_service.producer.model;


import com.patika.auth_service.producer.model.enums.NotificationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Notification {

    private NotificationType notificationType;

    private String notificationUser;

    private String notificationMessage;

    private LocalDateTime notificationTime;

}
