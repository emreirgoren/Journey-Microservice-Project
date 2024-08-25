package com.patika.journey_index_service.repository;

import com.patika.journey_index_service.model.Journey;
import com.patika.journey_index_service.model.document.JourneyDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IndexRepository extends ElasticsearchRepository<JourneyDocument,Long> {
}
