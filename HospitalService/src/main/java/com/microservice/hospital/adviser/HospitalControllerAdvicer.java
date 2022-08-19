package com.microservice.hospital.adviser;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HospitalControllerAdvicer extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
		return new ResponseEntity<String>("The given entity is null, Please have a look!", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
		return new ResponseEntity<String>("The given value is not present in DB, Please change your request!",
				HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>("Please change your http method type ", HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<String> handleInternalServerError(InternalServerError internalServerError) {
		return new ResponseEntity<String>("Server is down try after sometime!",
				HttpStatus.INTERNAL_SERVER_ERROR);
	} 

}
