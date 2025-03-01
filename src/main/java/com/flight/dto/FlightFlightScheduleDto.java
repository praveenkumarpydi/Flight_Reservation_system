package com.flight.dto;

import java.time.LocalDate;

public class FlightFlightScheduleDto {

	private Long flightId;
	
	private Long flightSchduleId;
	
    private String flightNumber;
    
    private String airline;
    
    private String source;
    
    private String destination;
    
    private String sourceTime;
    
    private String destinationTime;
    
    private LocalDate travelDate;

    private int availableSeats;
    
    private double price;
    
    

    
	

	public Long getFlightSchduleId() {
		return flightSchduleId;
	}

	public void setFlightSchduleId(Long flightSchduleId) {
		this.flightSchduleId = flightSchduleId;
	}

	public FlightFlightScheduleDto() {
	}

	private FlightFlightScheduleDto(Builder builder) {
		this.flightId = builder.flightId;
		this.flightNumber = builder.flightNumber;
		this.airline = builder.airline;
		this.source = builder.source;
		this.flightSchduleId = builder.flightSchduleId;
		this.destination = builder.destination;
		this.sourceTime = builder.sourceTime;
		this.destinationTime = builder.destinationTime;
		this.travelDate = builder.travelDate;
		this.availableSeats = builder.availableSeats;
		this.price = builder.price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
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

	public String getSourceTime() {
		return sourceTime;
	}

	public void setSourceTime(String sourceTime) {
		this.sourceTime = sourceTime;
	}

	public String getDestinationTime() {
		return destinationTime;
	}

	public void setDestinationTime(String destinationTime) {
		this.destinationTime = destinationTime;
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
    
    public static class  Builder{

    	private Long flightId;
    	
    	private Long flightSchduleId;
    	
        private String flightNumber;
        
        private String airline;
        
        private String source;
        
        private String destination;
        
        private String sourceTime;
        
        private String destinationTime;
        
        private LocalDate travelDate;

        private int availableSeats;
        
        private double price;

		public Builder setFlightId(Long flightId) {
			this.flightId = flightId;
			return this;
		}

		public Builder setFlightNumber(String flightNumber) {
			this.flightNumber = flightNumber;
			return this;
		}

		public Builder setAirline(String airline) {
			this.airline = airline;
			return this;
		}

		public Builder setFlightScheduleId(Long flightScheduleId) {
			this.flightSchduleId = flightScheduleId;
			return this;
		}
		public Builder setSource(String source) {
			this.source = source;
			return this;
		}

		public Builder setDestination(String destination) {
			this.destination = destination;
			return this;
		}

		public Builder setSourceTime(String sourceTime) {
			this.sourceTime = sourceTime;
			return this;
		}

		public Builder setDestinationTime(String destinationTime) {
			this.destinationTime = destinationTime;
			return this;
		}

		public Builder setTravelDate(LocalDate travelDate) {
			this.travelDate = travelDate;
			return this;
		}

		public Builder setAvailableSeats(int availableSeats) {
			this.availableSeats = availableSeats;
			return this;
		}

		public Builder setPrice(double price) {
			this.price = price;
			return this;
		}
		
		public FlightFlightScheduleDto build() {
			return new FlightFlightScheduleDto(this);
		}
        
        
    }
    
}
