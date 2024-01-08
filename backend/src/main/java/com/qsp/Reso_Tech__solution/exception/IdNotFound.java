package com.qsp.Reso_Tech__solution.exception;

public class IdNotFound extends RuntimeException {
	
	private String message;
	
	public IdNotFound(String messsage) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
