package com.patika.ticket_search_service.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JourneySearchByCityRequest {

    private int page;
    private String fromCity;
    private String toCity;

}
