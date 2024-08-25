package com.patika.auth_service.producer;

import com.patika.auth_service.config.RabbitMQProducerConfig;
import com.patika.auth_service.producer.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitProducer {

    private final AmqpTemplate rabbitTemplate;

    private final RabbitMQProducerConfig rabbitMQProducerConfig;

    public RabbitProducer(AmqpTemplate rabbitTemplate, RabbitMQProducerConfig rabbitMQProducerConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQProducerConfig = rabbitMQProducerConfig;
    }

    public void sendNotification(Notification notification) {

        rabbitTemplate.convertAndSend(rabbitMQProducerConfig.getExchange(), rabbitMQProducerConfig.getRoutingkey(),notification );

        log.info("message kuyruğa gönderildi. kuyruk:{}, message: {}", rabbitMQProducerConfig.getNotificationQueue(), notification);

    }

}
