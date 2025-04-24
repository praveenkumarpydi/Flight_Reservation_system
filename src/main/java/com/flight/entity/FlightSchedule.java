package com.flight.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FlightSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long flightScheduleId;

	@ManyToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;

	private LocalDate travelDate;

	private int availableSeats; // Track available seats

	public Long getFlightScheduleId() {
		return flightScheduleId;
	}

	public void setFlightScheduleId(Long flightScheduleId) {
		this.flightScheduleId = flightScheduleId;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	// Getters and Setters
}
