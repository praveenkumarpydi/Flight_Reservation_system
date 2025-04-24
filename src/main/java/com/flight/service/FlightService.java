package com.flight.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.dto.FlightFlightScheduleDto;
import com.flight.entity.Flight;
import com.flight.enums.FlightStatus;
import com.flight.projections.FlightFlightScheduleProjection;
import com.flight.repo.FlightRepository;

@Service
public class FlightService {

	@Autowired
	private FlightRepository flightRepository;

	public List<FlightFlightScheduleDto> searchFlights(String source, String destination, LocalDate travelDate) {
		System.out.println("Request Riceved To FlightService");
		List<FlightFlightScheduleProjection> projection = flightRepository
				.findBySourceAndDestinationAndTravelDate(source, destination, travelDate);
		return projection.stream().map(projecionObj -> {
			return new FlightFlightScheduleDto.Builder().setAirline(projecionObj.getAirline())
					.setAvailableSeats(projecionObj.getAvailableSeats()).setDestination(projecionObj.getDestination())
					.setDestinationTime(projecionObj.getDestinationTime()).setFlightId(projecionObj.getFlightId())
					.setFlightScheduleId(projecionObj.getFlightScheduleId())
					.setFlightNumber(projecionObj.getFlightNumber()).setPrice(projecionObj.getPrice())
					.setSource(projecionObj.getSource()).setSourceTime(projecionObj.getSourceTime())
					.setTravelDate(projecionObj.getTravelDate()).build();
		}).collect(Collectors.toList());
	}

	public Flight addFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	
	public String updateFlight(Long flightId, String status) {
		Flight flight = findFlightById(flightId);
		    if(flight == null)
		    	return "flight with id "+ flightId + " is Not found";
		if(status.equalsIgnoreCase(FlightStatus.CANCELED.toString())) {
			flight.setStatus(FlightStatus.CANCELED);
		}
		else if(status.equalsIgnoreCase(FlightStatus.DELAYED.toString())) {
			flight.setStatus(FlightStatus.DELAYED); 
		}
		else if(status.equalsIgnoreCase(FlightStatus.ACTIVE.toString())) {
			flight.setStatus(FlightStatus.ACTIVE); 
		}
		else {
			return status +" is NOt allowed";
		}
		flightRepository.save(flight);
		return "success";
	}

	public Flight findFlightById(Long flightId) {
		return flightRepository.findById(flightId).get();
		
	}
}