package com.patika.journey_service.service.businessRules;

import com.patika.journey_service.dto.request.JourneyCancelledRequest;
import com.patika.journey_service.dto.request.JourneyCreateRequest;
import com.patika.journey_service.dto.response.GenericResponse.GenericResponse;
import com.patika.journey_service.dto.response.JourneyCreateResponse;
import com.patika.journey_service.exception.JourneyAlreadyCancelledException;
import com.patika.journey_service.exception.JourneyNotFoundException;
import com.patika.journey_service.exception.LimitExceededJourneyException;
import com.patika.journey_service.model.Journey;
import com.patika.journey_service.model.enums.VehicleType;
import com.patika.journey_service.producer.KafkaProducer;
import com.patika.journey_service.repository.JourneyJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class JourneyRules {

    private final JourneyJpaRepository journeyJpaRepository;
    private final KafkaProducer kafkaProducer;

    public JourneyRules(JourneyJpaRepository journeyJpaRepository, KafkaProducer kafkaProducer) {
        this.journeyJpaRepository = journeyJpaRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public ResponseEntity<GenericResponse<JourneyCreateResponse>> limitExceededJourneyRule(JourneyCreateRequest request){
        LocalDate departureDate = request.getDepartureDate();
        String fromCity = request.getFromCity();
        String toCity = request.getToCity();
        VehicleType vehicleType = request.getVehicleType();

        Long count = journeyJpaRepository.countByDepartureDateAndFromCityAndToCityAndVehicleType(departureDate, fromCity, toCity, vehicleType);

        if (count >= 3) {

            throw new LimitExceededJourneyException("Limit Aşıldı");
        }
        return null;
    }

    public void journeyNotFound(JourneyCancelledRequest request){
        Optional<Journey> optionalJourney = journeyJpaRepository.findByTicketCode(request.getTicketCode());
        if (optionalJourney.isEmpty()) {

            throw new JourneyNotFoundException("Sefer Bulunamadı.");
        }
    }

    public ResponseEntity<GenericResponse<Object>> journeyIsCancelled(Journey journey){

        if(journey.isCancelled()){
            throw new JourneyAlreadyCancelledException("Sefer iptal edilmiş.");
        }
        return null;
    }





}
