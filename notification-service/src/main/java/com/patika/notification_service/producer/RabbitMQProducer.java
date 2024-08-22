package com.patika.notification_service.producer;


import com.patika.notification_service.config.RabbitMQProducerConfig;
import com.patika.notification_service.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQProducer {

    private final AmqpTemplate rabbitTemplate;

    private final RabbitMQProducerConfig rabbitMQProducerConfig;

    public RabbitMQProducer(AmqpTemplate rabbitTemplate, RabbitMQProducerConfig rabbitMQProducerConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQProducerConfig = rabbitMQProducerConfig;
    }

    public void sendPushNotification(String message) {

        rabbitTemplate.convertAndSend(rabbitMQProducerConfig.getExchange(), rabbitMQProducerConfig.getRoutingkey(),message );

        log.info("message kuyruğa gönderildi. kuyruk:{}, message: {}", rabbitMQProducerConfig.getPushNotificationQueue(), message);

        log.info("Message: " + message + " Message Type: " );



    }

}
