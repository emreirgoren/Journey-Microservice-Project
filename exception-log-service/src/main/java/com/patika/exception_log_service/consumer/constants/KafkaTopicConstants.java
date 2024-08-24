package com.patika.exception_log_service.consumer.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaTopicConstants {

    public static final String EXCEPTION_TOPIC = "exception-topic";
    public static final String LOG_TOPIC = "log-topic";

}
