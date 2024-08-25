package com.patika.ticket_search_service.service;

import com.patika.ticket_search_service.converter.JourneyConverter;
import com.patika.ticket_search_service.dto.request.JourneySearchByCityAndDepartureDateRequest;
import com.patika.ticket_search_service.dto.request.JourneySearchByCityAndVehicleTypeRequest;
import com.patika.ticket_search_service.dto.request.JourneySearchByCityRequest;
import com.patika.ticket_search_service.dto.response.JourneySearchResponse;
import com.patika.ticket_search_service.exception.JourneyNotFoundException;
import com.patika.ticket_search_service.model.Journey;
import com.patika.ticket_search_service.repository.JourneyElasticsearchRepository;
import com.patika.ticket_search_service.service.businessRules.TicketRules;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TicketService {

    private final JourneyElasticsearchRepository journeyElasticsearchRepository;

    private final TicketRules ticketRules;

    public TicketService(JourneyElasticsearchRepository elasticsearchRepository, TicketRules ticketRules) {
        this.journeyElasticsearchRepository = elasticsearchRepository;
        this.ticketRules = ticketRules;
    }

    public List<JourneySearchResponse> getTicketListByCity(JourneySearchByCityRequest request) {

        Sort sort = Sort.by("departureDate").ascending();

        Pageable pageable = PageRequest.of(request.getPage(), 10, sort);
        List<Journey> journeyList = journeyElasticsearchRepository.searchByFromCityAndToCity(
                request.getFromCity(),
                request.getToCity(),
                pageable);

        ticketRules.journeyListIsEmpty(journeyList);

        List<JourneySearchResponse> journeySearchResponseList = journeyList.stream()
                .map(JourneyConverter::toJourneySearchResponse)
                .collect(Collectors.toList());

        return journeySearchResponseList;
    }

    public List<JourneySearchResponse> getTicketListByCityAndVehicleType(JourneySearchByCityAndVehicleTypeRequest request) {

        Sort sort = Sort.by("departureDate").ascending();
        Pageable pageable = PageRequest.of(request.getPage(), 10, sort);

        List<Journey> journeyList = journeyElasticsearchRepository.searchByFromCityAndToCityAndVehicleType(
                request.getFromCity(),
                request.getToCity(),
                request.getVehicleType(),
                pageable);

        ticketRules.journeyListIsEmpty(journeyList);

        List<JourneySearchResponse> journeySearchResponseList = journeyList.stream()
                .map(JourneyConverter::toJourneySearchResponse)
                .collect(Collectors.toList());
        return journeySearchResponseList;
    }

    public List<JourneySearchResponse> getTicketListByCityAndDepartureDate(JourneySearchByCityAndDepartureDateRequest request) {

        Sort sort = Sort.by("departureDate").ascending();
        Pageable pageable = PageRequest.of(request.getPage(), 10, sort);

        List<Journey> journeyList = journeyElasticsearchRepository.searchByFromCityAndToCityAndDepartureDate(
                request.getFromCity(),
                request.getToCity(),
                request.getDepartureDate(),
                pageable);

        ticketRules.journeyListIsEmpty(journeyList);

        List<JourneySearchResponse> journeySearchResponseList = journeyList.stream()
                .map(JourneyConverter::toJourneySearchResponse)
                .collect(Collectors.toList());
        return journeySearchResponseList;
    }

    public Journey getTicketByTicketCode(String ticketCode) {

        Optional<Journey> optionalJourney = journeyElasticsearchRepository.findByTicketCode(ticketCode);

        if (optionalJourney.isEmpty()) {
            throw new JourneyNotFoundException("Sefer Bulunamadı.");
        }
        return optionalJourney.get();
    }
}
