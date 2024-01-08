package com.qsp.Reso_Tech__solution.exception;

public class NameNotFound extends RuntimeException{

private String message;
	
	public NameNotFound(String message) {
		this.message = message ;
	}
	
	public String getMessage()
	{
		return message;
	}
}
