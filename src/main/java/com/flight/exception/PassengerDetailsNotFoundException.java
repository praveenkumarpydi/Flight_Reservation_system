package com.flight.exception;

public class PassengerDetailsNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PassengerDetailsNotFoundException(String messege) {
		super(messege);
	}
}
