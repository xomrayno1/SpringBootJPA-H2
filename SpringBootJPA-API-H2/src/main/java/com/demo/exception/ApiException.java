package com.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiException {
	private  final	String message;
	//private	 final	Throwable throwable;
	private  final 	HttpStatus httpStatus;
	private  final	Date date;
	 
//	public ApiException(String message, Throwable throwable, HttpStatus httpStatus, Date date) {
//		super();
//		this.message = message;
//		this.throwable = throwable;
//		this.httpStatus = httpStatus;
//		this.date = date;
//	}
	
	
	public String getMessage() {
		return message;
	}
	public ApiException(String message, HttpStatus httpStatus, Date date) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.date = date;
	}
//	public Throwable getThrowable() {
//		return throwable;
//	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public Date getDate() {
		return date;
	}
	  
	
	
	
	

}
