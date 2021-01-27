package com.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiHandleException {

	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException requestException){
		ApiException apiException =
					new ApiException(requestException.getMessage(),							 
								requestException.getHttpStatus(),
							 new Date());
		return new ResponseEntity<Object>(apiException,requestException.getHttpStatus());
	}
}
