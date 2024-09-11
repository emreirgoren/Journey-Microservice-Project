package com.patika.journey_service.consumer;

import com.patika.journey_service.producer.constant.KafkaTopicConstants;
import com.patika.journey_service.service.impl.JourneyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class KafkaConsumer {

    private final JourneyServiceImpl journeyService;

    public KafkaConsumer(JourneyServiceImpl journeyService) {
        this.journeyService = journeyService;
    }

    @KafkaListener(topics = KafkaTopicConstants.ORDER,groupId = "${spring.kafka.bootstrap-servers}",containerFactory = "kafkaListenerContainerFactoryOrderMap")
    public void listenOrderMap(Map<String,Integer> forSalesRepost){
        log.info("Map : " + forSalesRepost);
        journeyService.forSalesReport(forSalesRepost);
    }

}
