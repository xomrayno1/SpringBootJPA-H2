package com.demo.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException  extends RuntimeException{
	private  HttpStatus httpStatus;
	private  String messages;
	public ApiRequestException(String msg,HttpStatus httpStatus) {
		//super(msg);
		this(msg);
		this.httpStatus = httpStatus;
	}
	public ApiRequestException(String msg) {
		this.messages = msg;
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
	public String getMessages() {
		return messages;
	}
	public void setMessages(String message) {
		this.messages = message;
	}
	 
	
	
}
