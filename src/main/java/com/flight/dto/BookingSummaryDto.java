package com.flight.dto;

public class BookingSummaryDto {
    private Long bookingId;
    private String bookingStatus;
    private Double totalPrice;

	public BookingSummaryDto(Long bookingId, String bookingStatus, Double totalPrice) {
		this.bookingId = bookingId;
		this.bookingStatus = bookingStatus;
		this.totalPrice = totalPrice;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

	
}
