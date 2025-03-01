package com.flight.projections;

public interface BookingSummaryProjection {
    Long getBookingId();
    String getBookingStatus();
    Double getTotalPrice();
}

