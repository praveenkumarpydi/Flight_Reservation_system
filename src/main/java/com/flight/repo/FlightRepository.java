package com.flight.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flight.entity.Flight;
import com.flight.projections.FlightFlightScheduleProjection;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	@Query(value = "SELECT f.flight_id, f.flight_number, f.airline, f.source, f.destination, f.source_time, f.destination_time, fs.travel_date, fs.available_seats, fs.flight_schedule_id, f.price FROM flight f JOIN flight_schedule fs ON f.flight_id = fs.flight_id WHERE f.source =:source AND f.destination =:destination AND fs.travel_date =:travelDate AND fs.available_seats > 0", nativeQuery = true)
	List<FlightFlightScheduleProjection> findBySourceAndDestinationAndTravelDate(String source, String destination,
			LocalDate travelDate);
}
