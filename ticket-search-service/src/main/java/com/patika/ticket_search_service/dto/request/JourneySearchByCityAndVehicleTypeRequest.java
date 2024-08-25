package com.patika.ticket_search_service.dto.request;

import com.patika.ticket_search_service.model.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JourneySearchByCityAndVehicleTypeRequest {

    private int page;
    private String fromCity;
    private String toCity;
    private VehicleType vehicleType;

}
