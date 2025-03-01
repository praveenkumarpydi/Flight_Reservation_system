package com.flight.exception;

public class InSufficientSeatsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InSufficientSeatsException(String message) {
		super(message);
	}
}
