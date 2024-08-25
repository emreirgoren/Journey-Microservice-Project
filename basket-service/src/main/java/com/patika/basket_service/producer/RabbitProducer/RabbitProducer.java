package com.patika.basket_service.producer.RabbitProducer;

import com.patika.basket_service.config.RabbitMQProducerConfig;
import com.patika.basket_service.model.Ticket;
import com.patika.basket_service.producer.RabbitProducer.model.Notification;
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
