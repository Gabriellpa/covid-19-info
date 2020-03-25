package com.spring.cov19.exceptions;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Exceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IOException.class })
    	    protected ResponseEntity<Object> handleConflict(
    	      RuntimeException ex, WebRequest request) {
    	        String bodyOfResponse = "ERERREOROEROE";
    	        return handleExceptionInternal(ex, bodyOfResponse, 
    	          new HttpHeaders(), HttpStatus.CONFLICT, request);
    	    }
	
}
