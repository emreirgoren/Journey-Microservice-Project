package com.patika.ticket_search_service.repository;

import com.patika.ticket_search_service.dto.response.JourneySearchResponse;
import com.patika.ticket_search_service.model.Journey;
import com.patika.ticket_search_service.model.enums.VehicleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface JourneyElasticsearchRepository extends ElasticsearchRepository<Journey,Long> {
    List<Journey> searchByFromCityAndToCity(String fromCity, String toCity, Pageable pageable);

    List<Journey> searchByFromCityAndToCityAndVehicleType(String fromCity, String fromCity1, VehicleType vehicleType, Pageable pageable);

    List<Journey> searchByFromCityAndToCityAndDepartureDate(String fromCity, String toCity, String departureDate, Pageable pageable);

    Optional<Journey> findByTicketCode(String ticketCode);

}
