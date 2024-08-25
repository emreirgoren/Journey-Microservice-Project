package com.patika.basket_service.producer.KafkaProducer.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaTopicConstants {

    public static final String EXCEPTION_TOPIC = "exception-topic";
    public static final String LOG_TOPIC = "log-topic";
    public static final String JOURNEY = "basket-topic";
}
