package com.patika.notification_service.model;

import com.patika.notification_service.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
/*
@AllArgsConstructor
@NoArgsConstructor*/
@Getter
@Setter
@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;

    private NotificationType notificationType;

    private String notificationUser;

    private String notificationMessage;

    private LocalDateTime notificationTime;

}
