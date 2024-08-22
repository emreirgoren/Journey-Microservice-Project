package com.patika.notification_service.strategy;

import com.patika.notification_service.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailNotificationStrategy implements NotificationStrategy{

    private JavaMailSender mailSender;

    public EmailNotificationStrategy(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(Notification notification) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notification.getNotificationUser());
        message.setSubject("Notification test mail");
        message.setText(notification.getNotificationMessage());
        mailSender.send(message);

        log.info("Mail Gonderildi");
    }

}
