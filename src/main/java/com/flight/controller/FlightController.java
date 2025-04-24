package com.flight.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.dto.FlightFlightScheduleDto;
import com.flight.entity.Flight;
import com.flight.enums.FlightStatus;
import com.flight.service.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;

	@PutMapping("/update/{flightId}/{status}")
	public String updateFlightStatus(@PathVariable("flightId") Long flightId, @PathVariable("status") String status) {
		return flightService.updateFlight(flightId, status);

	}

	@GetMapping("/search")
	public List<FlightFlightScheduleDto> searchFlights(@RequestParam String source, @RequestParam String destination,
			@RequestParam LocalDate travelDate) {
		System.out.println("Request Riceved To FlightController");
		return flightService.searchFlights(source, destination, travelDate);
	}

	@PostMapping("/add")
	public Flight addFlight(@RequestBody Flight flight) {
		return flightService.addFlight(flight);
	}
}
