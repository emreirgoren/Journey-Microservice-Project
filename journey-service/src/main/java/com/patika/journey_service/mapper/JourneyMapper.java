package com.patika.journey_service.mapper;


import com.patika.journey_service.dto.request.JourneyCreateRequest;
import com.patika.journey_service.dto.response.JourneyCancelledResponse;
import com.patika.journey_service.dto.response.JourneyCreateResponse;
import com.patika.journey_service.model.Journey;
import com.patika.journey_service.model.enums.VehicleType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JourneyMapper {


    public static Journey journeyCreateRequestToJourney(JourneyCreateRequest journeyCreateRequest){
        Journey journey = new Journey();
        journey.setFromCity(journeyCreateRequest.getFromCity());
        journey.setToCity(journeyCreateRequest.getToCity());
        journey.setVehicleType(journeyCreateRequest.getVehicleType());
        journey.setDepartureDate(journeyCreateRequest.getDepartureDate());
        journey.setDepartureTime(journeyCreateRequest.getDepartureTime());
        if(journeyCreateRequest.getVehicleType().equals(VehicleType.BUS)){
            journey.setCapacity(45);
            journey.setAvailableTicket(45);
        }else{
            journey.setCapacity(189);
            journey.setAvailableTicket(189);
        }
        journey.setCancelled(false);
        journey.setTicketPrice(journeyCreateRequest.getTicketPrice());
        return journey;
    }

    public static JourneyCreateResponse journeyToJourneyCreateResponse(Journey journey){
        JourneyCreateResponse journeyCreateResponse = new JourneyCreateResponse();
        journeyCreateResponse.setTicketCode(journey.getTicketCode());
        journeyCreateResponse.setFromCity(journey.getFromCity());
        journeyCreateResponse.setToCity(journey.getToCity());
        journeyCreateResponse.setVehicleType(journey.getVehicleType());
        journeyCreateResponse.setDepartureDate(journey.getDepartureDate());
        journeyCreateResponse.setDepartureTime(journey.getDepartureTime());
        journeyCreateResponse.setCapacity(journey.getCapacity());
        journeyCreateResponse.setCancelled(false);
        journeyCreateResponse.setTicketPrice(journey.getTicketPrice());
        journeyCreateResponse.setAvailableTicket(journey.getAvailableTicket());
        return journeyCreateResponse;
    }

    public static JourneyCancelledResponse journeyToJourneyCancelledResponse(Journey journey) {
        JourneyCancelledResponse journeyCancelledResponse = new JourneyCancelledResponse();
        journeyCancelledResponse.setTicketCode(journey.getTicketCode());
        journeyCancelledResponse.setFromCity(journey.getFromCity());
        journeyCancelledResponse.setToCity(journey.getToCity());
        journeyCancelledResponse.setVehicleType(journey.getVehicleType());
        journeyCancelledResponse.setDepartureDate(journey.getDepartureDate());
        journeyCancelledResponse.setDepartureTime(journey.getDepartureTime());
        journeyCancelledResponse.setCapacity(journey.getCapacity());
        journeyCancelledResponse.setCancelled(true);
        journeyCancelledResponse.setTicketPrice(journey.getTicketPrice());
        journeyCancelledResponse.setCreatedDateTime(journey.getCreatedDateTime());

        return journeyCancelledResponse;
    }
}
