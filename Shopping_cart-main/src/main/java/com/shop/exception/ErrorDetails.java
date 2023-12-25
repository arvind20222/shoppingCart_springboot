package com.shop.exception;

import java.util.Date;

public class ErrorDetails {
	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	private Date timestamp;
	private String message;
	private String details;
	public Date getTimestamp() {
		return timestamp;
	}
	public String getDetails() {
		return details;
	}
	public String getMessage() {
		return message;
	}



 
}
