package com.patika.journey_index_service.consumer;

import com.patika.journey_index_service.consumer.constant.KafkaTopicConstants;
import com.patika.journey_index_service.model.Journey;
import com.patika.journey_index_service.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    private final IndexService indexService;

    public KafkaConsumer(IndexService indexService) {
        this.indexService = indexService;
    }

    @KafkaListener(topics = KafkaTopicConstants.JOURNEY, groupId = "${kafka.journey.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void listenEx(Journey journey) {
        indexService.createTicket(journey);
        log.info("Journey oluşturuldu: " , journey);
    }



}
