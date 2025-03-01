package com.flight.exception;

public class UserNameAlredyExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNameAlredyExistException(String message) {
		super(message);
	}
}
