package com.demo.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException  extends RuntimeException{
	private  HttpStatus httpStatus;
	private  String message;
	public ApiRequestException(String msg,HttpStatus httpStatus) {
		//super(msg);
		this(msg);
		this.httpStatus = httpStatus;
	}
	public ApiRequestException(String msg) {
		this.message = msg;
	}
	public ApiRequestException(HttpStatus httpStatus) {
		this.httpStatus = httpStatus; 
	}
	public ApiRequestException(Throwable throwable) {
		super(throwable);	 		 
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	 
	
	
}
