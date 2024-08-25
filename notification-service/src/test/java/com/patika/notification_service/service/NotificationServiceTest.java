package com.patika.notification_service.service;

import com.patika.notification_service.model.Notification;
import com.patika.notification_service.model.enums.NotificationType;
import com.patika.notification_service.repository.NotificationRepository;
import com.patika.notification_service.strategy.EmailNotificationStrategy;
import com.patika.notification_service.strategy.NotificationContext;
import com.patika.notification_service.strategy.PushNotificationStrategy;
import com.patika.notification_service.strategy.SmsNotificationStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {


    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationContext notificationContext;

    @Mock
    private SmsNotificationStrategy smsNotificationStrategy;

    @Mock
    private EmailNotificationStrategy emailNotificationStrategy;

    @Mock
    private PushNotificationStrategy pushNotificationStrategy;

    @Test
    void sendSmsNotification(){
        //given
        Notification notification = new Notification();
        notification.setNotificationType(NotificationType.SMS);

        //when
        notificationService.sendNotification(notification);

        //then
        Mockito.verify(notificationRepository,times(1)).save(notification);
        Mockito.verify(notificationContext,times(1)).setNotificationStrategy(smsNotificationStrategy);
        Mockito.verify(notificationContext,times(1)).executeStrategy(notification);
    }

    @Test
    void sendEmailNotification(){
        //given
        Notification notification = new Notification();
        notification.setNotificationType(NotificationType.EMAIL);
        notification.setNotificationUser("test@gmail.com");
        notification.setNotificationMessage("test-message");
        notification.setNotificationTime(LocalDateTime.now());

        //when
        notificationService.sendNotification(notification);

        //then
        Mockito.verify(notificationRepository,times(1)).save(notification);
        Mockito.verify(notificationContext,times(1)).setNotificationStrategy(emailNotificationStrategy);
        Mockito.verify(notificationContext,times(1)).executeStrategy(notification);

    }

    @Test
    void sendPushNotification(){
        //given
        Notification notification = new Notification();
        notification.setNotificationType(NotificationType.PUSH);

        //when
        notificationService.sendNotification(notification);

        //then
        Mockito.verify(notificationRepository,times(1)).save(notification);
        Mockito.verify(notificationContext,times(1)).setNotificationStrategy(pushNotificationStrategy);
        Mockito.verify(notificationContext,times(1)).executeStrategy(notification);
    }

}
