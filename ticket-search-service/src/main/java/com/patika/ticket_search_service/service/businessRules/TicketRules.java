package com.patika.ticket_search_service.service.businessRules;

import com.patika.ticket_search_service.exception.JourneyNotFoundException;
import com.patika.ticket_search_service.model.Journey;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketRules {

    public void journeyListIsEmpty(List<Journey> journeyList){

        if(journeyList.isEmpty()){
            throw new JourneyNotFoundException("Sefer Bulunamadı.");
        }

    }

}
