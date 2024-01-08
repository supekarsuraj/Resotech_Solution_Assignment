package com.qsp.Reso_Tech__solution.exception;

public class TaskNameNotFound extends RuntimeException{

private String message;
	
	public TaskNameNotFound(String messsage) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
