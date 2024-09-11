package com.patika.basket_service.producer.KafkaProducer;

import com.patika.basket_service.producer.KafkaProducer.constant.KafkaTopicConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Send Exception to exception-log-service
     */
    public void sendException(ExceptionTemplate exceptionTemplate){
        kafkaTemplate.send(KafkaTopicConstants.EXCEPTION_TOPIC,exceptionTemplate);
        log.info("Exception Gonderildi");
    }

    public void sendLog(LogTemplate logTemplate){
        log.info("log gönderildi");
            kafkaTemplate.send(KafkaTopicConstants.LOG_TOPIC,logTemplate);
    }

    public void sendOrderMap(Map forSalesReport){
        kafkaTemplate.send(KafkaTopicConstants.ORDER,forSalesReport);
    }



}


