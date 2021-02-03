package com.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiException {
	private  final	String message;
	 
	private  final 	int code;
	private  final String description;
	private  final	Date date;
	 
 
	
	
	public String getMessage() {
		return message;
	}
 
 
	 
	public ApiException(String message, int code, String description, Date date) {
		super();
		this.message = message;
		this.code = code;
		this.description = description;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}
	public int getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
	  
	
	
	
	

}
