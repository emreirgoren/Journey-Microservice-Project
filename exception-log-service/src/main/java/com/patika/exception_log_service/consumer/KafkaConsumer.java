package com.patika.exception_log_service.consumer;


import com.patika.exception_log_service.consumer.constants.KafkaTopicConstants;
import com.patika.exception_log_service.model.Exception;
import com.patika.exception_log_service.model.Log;
import com.patika.exception_log_service.service.ExceptionService;
import com.patika.exception_log_service.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class KafkaConsumer {

    private final ExceptionService exceptionService;
    private final LogService logService;

    public KafkaConsumer(ExceptionService exceptionService, LogService logService) {
        this.exceptionService = exceptionService;
        this.logService = logService;
    }

    @KafkaListener(topics = KafkaTopicConstants.EXCEPTION_TOPIC, groupId = "${kafka.journey.group-id}", containerFactory = "kafkaListenerContainerFactoryException")
    public void listenEx(ExceptionTemplate exceptionTemplate) {
        exceptionService.handleException(exceptionTemplate);
    }

    @KafkaListener(topics = KafkaTopicConstants.LOG_TOPIC, groupId = "${kafka.journey.group-id}", containerFactory = "kafkaListenerContainerFactoryLog")
    public void listenLog(LogTemplate logTemplate) {
        logService.handleLog(logTemplate);

    }


}
