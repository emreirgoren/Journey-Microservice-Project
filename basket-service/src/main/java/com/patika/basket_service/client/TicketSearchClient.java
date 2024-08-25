package com.patika.basket_service.client;

import com.patika.basket_service.model.Journey;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ticket-search-service",url = "localhost:9014/api/v1/searches")
public interface TicketSearchClient {

    @GetMapping("/{ticketCode}")
    Journey getTicket(@PathVariable String ticketCode);

}
