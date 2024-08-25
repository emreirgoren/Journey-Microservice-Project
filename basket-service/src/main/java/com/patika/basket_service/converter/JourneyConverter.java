package com.patika.basket_service.converter;

import com.patika.basket_service.model.Journey;
import com.patika.basket_service.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class JourneyConverter {

    public static Ticket journeyToTicket(Journey journey){

        Ticket ticket = new Ticket();

        ticket.setTicketCode(journey.getTicketCode());
        ticket.setFromCity(journey.getFromCity());
        ticket.setToCity(journey.getToCity());
        ticket.setVehicleType(journey.getVehicleType());
        ticket.setDepartureDate(journey.getDepartureDate());
        ticket.setDepartureTime(journey.getDepartureTime());
        ticket.setTicketPrice(journey.getTicketPrice());

        return ticket;
    }

}
