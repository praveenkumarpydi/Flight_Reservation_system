package com.flight.projections;

import java.time.LocalDate;

public interface FlightFlightScheduleProjection {
    Long getFlightId();
    Long getFlightScheduleId();
    String getFlightNumber();
    String getAirline();
    String getSource();
    String getDestination();
    String getSourceTime();
    String getDestinationTime();
    LocalDate getTravelDate();
    int getAvailableSeats();
    double getPrice();
}
