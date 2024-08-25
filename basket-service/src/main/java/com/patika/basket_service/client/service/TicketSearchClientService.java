package com.patika.basket_service.client.service;

import com.patika.basket_service.client.TicketSearchClient;
import com.patika.basket_service.model.Journey;
import org.springframework.stereotype.Service;

@Service
public class TicketSearchClientService {

    private final TicketSearchClient ticketSearchClient;

    public TicketSearchClientService(TicketSearchClient ticketSearchClient) {
        this.ticketSearchClient = ticketSearchClient;
    }

    public Journey getTicket(String ticketCode){
        return ticketSearchClient.getTicket(ticketCode);
    }
}
