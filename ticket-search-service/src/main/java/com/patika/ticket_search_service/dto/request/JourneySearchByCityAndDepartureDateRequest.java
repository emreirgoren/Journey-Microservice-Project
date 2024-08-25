package com.patika.ticket_search_service.dto.request;

import com.patika.ticket_search_service.model.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JourneySearchByCityAndDepartureDateRequest {

    private int page;
    private String fromCity;
    private String toCity;
    private String departureDate;

}
