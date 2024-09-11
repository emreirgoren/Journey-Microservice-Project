package com.patika.journey_service.producer.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaTopicConstants {

    public static final String EXCEPTION_TOPIC = "exception-topic";
    public static final String LOG_TOPIC = "log-topic";
    public static final String JOURNEY = "journey-topic";

    public static final String ORDER = "order-topic";

}
