package com.patika.ticket_search_service.producer.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaTopicConstants {

    public static final String EXCEPTION_TOPIC = "exception-topic";
    public static final String LOG_TOPIC = "log-topic";

}
