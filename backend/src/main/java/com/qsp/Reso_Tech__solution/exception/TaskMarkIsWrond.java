package com.qsp.Reso_Tech__solution.exception;

public class TaskMarkIsWrond extends RuntimeException{
	private String massage;

	public TaskMarkIsWrond(String massage) {
		super();
		this.massage = massage;
	}
	

}
