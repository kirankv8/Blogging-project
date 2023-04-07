package com.application.blogging.exceptionhandler;

public class ResourceNotFoundException extends Exception {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer statuscode;

	public Integer getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(Integer statuscode) {
		this.statuscode = statuscode;
	}

	public ResourceNotFoundException(String message, Integer statuscode) {
		super(message);
		this.statuscode = statuscode;
	}
	
	
}
