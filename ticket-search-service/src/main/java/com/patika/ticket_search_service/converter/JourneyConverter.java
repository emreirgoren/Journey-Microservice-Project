package com.patika.ticket_search_service.converter;

import com.patika.ticket_search_service.dto.response.JourneySearchResponse;
import com.patika.ticket_search_service.model.Journey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JourneyConverter {

    public static JourneySearchResponse toJourneySearchResponse(Journey journey) {
        JourneySearchResponse journeySearchResponse = new JourneySearchResponse();

        journeySearchResponse.setTicketCode(journey.getTicketCode());
        journeySearchResponse.setFromCity(journey.getFromCity());
        journeySearchResponse.setToCity(journey.getToCity());
        journeySearchResponse.setVehicleType(journey.getVehicleType());
        journeySearchResponse.setDepartureDate(journey.getDepartureDate());
        journeySearchResponse.setDepartureTime(journey.getDepartureTime());
        journeySearchResponse.setCancelled(journey.isCancelled());
        journeySearchResponse.setTicketPrice(journey.getTicketPrice());
        journeySearchResponse.setAvailableTicket(journey.getAvailableTicket());

        return journeySearchResponse;
    }
}
