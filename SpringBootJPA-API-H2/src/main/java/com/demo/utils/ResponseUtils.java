package com.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.demo.response.APIResponse;

@Component
public class ResponseUtils {
	public static  ResponseEntity<APIResponse>  buildResponseSuccess(Object data){
		return new ResponseEntity<APIResponse>(
				new APIResponse<Object>(data
						,HttpStatus.OK.getReasonPhrase()
						,HttpStatus.OK.value())
				,HttpStatus.OK);
	}

}
