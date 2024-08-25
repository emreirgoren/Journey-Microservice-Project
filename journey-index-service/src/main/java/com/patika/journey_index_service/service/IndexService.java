package com.patika.journey_index_service.service;

import com.patika.journey_index_service.model.Journey;
import com.patika.journey_index_service.model.document.JourneyDocument;
import com.patika.journey_index_service.repository.IndexRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class IndexService {

    private final IndexRepository indexRepository;

    public IndexService(IndexRepository indexRepository) {
        this.indexRepository = indexRepository;
    }

    public void createTicket(Journey journey) {

        JourneyDocument journeyDocument = new JourneyDocument();
        journeyDocument.setId(journey.getId());
        journeyDocument.setTicketCode(journey.getTicketCode());
        journeyDocument.setFromCity(journey.getFromCity());
        journeyDocument.setToCity(journey.getToCity());
        journeyDocument.setVehicleType(journey.getVehicleType());

        LocalDate departureDate = journey.getDepartureDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = departureDate.format(formatter);
        journeyDocument.setDepartureDate(formattedDate);

        LocalTime departureTime = journey.getDepartureTime();
        DateTimeFormatter formatter2 =DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = departureTime.format(formatter2);
        journeyDocument.setDepartureTime(formattedTime);

        journeyDocument.setCancelled(journey.isCancelled());
        journeyDocument.setTicketPrice(journey.getTicketPrice());

        LocalDateTime createdDateTime = journey.getCreatedDateTime();
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String formattedCreateDateTime = createdDateTime.format(formatter3);
        journeyDocument.setCreatedDateTime(formattedCreateDateTime);

        journeyDocument.setAvailableTicket(journey.getAvailableTicket());
        indexRepository.save(journeyDocument);
    }

}
