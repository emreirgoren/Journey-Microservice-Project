package com.patika.journey_service.repository;


import com.patika.journey_service.model.Journey;
import com.patika.journey_service.model.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.Optional;

@Repository("journeyJpaRepository")
public interface JourneyJpaRepository extends JpaRepository<Journey,Long> {

    boolean existsByFromCityAndToCityAndVehicleTypeAndDepartureDate(String fromCity, String toCity, VehicleType vehicleType, LocalDate departureDate);

    Optional<Journey> findByFromCityAndToCityAndVehicleTypeAndDepartureDateAndIsCancelled(String fromCity, String toCity, VehicleType vehicleType, LocalDate departureDate, boolean cancelled);

    Optional<Journey> findByTicketCode(String ticketCode);

    Long countByDepartureDateAndFromCityAndToCityAndVehicleType(LocalDate departureTime, String fromCity, String toCity, VehicleType vehicleType);
}
