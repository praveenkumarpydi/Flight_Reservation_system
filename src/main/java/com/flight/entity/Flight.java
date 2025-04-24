package com.flight.entity;

import java.time.LocalTime;

import com.flight.enums.FlightStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long flightId;

	@Column(unique = true, nullable = false)
	private String flightNumber;

	@Column(nullable = false)
	private String airline;

	@Column(nullable = false)
	private String source;

	@Column(nullable = false)
	private String destination;

	@Column(nullable = false)
	private LocalTime sourceTime;

	@Column(nullable = false)
	private LocalTime destinationTime;

	@Column(nullable = false)
	private int totalSeats;

	@Column(nullable = false)
	private double price;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FlightStatus status = FlightStatus.ACTIVE;

	private String aircraftType; // e.g., Boeing 737, Airbus A320

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalTime getSourceTime() {
		return sourceTime;
	}

	public void setSourceTime(LocalTime sourceTime) {
		this.sourceTime = sourceTime;
	}

	public LocalTime getDestinationTime() {
		return destinationTime;
	}

	public void setDestinationTime(LocalTime destinationTime) {
		this.destinationTime = destinationTime;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	

}