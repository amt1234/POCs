package com.microservice.patient.adviser;

import java.util.NoSuchElementException;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.microservice.patient.exception.EmptyInputFieldException;

@ControllerAdvice
public class PatientControllerAdvicer extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmptyInputFieldException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputFieldException emptyInputFieldException) {
		return new ResponseEntity<>("Input field is empty, Please have a look!", HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
		return new ResponseEntity<>("The given value is not present in DB, Please change your request!",
				HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>("Please change your http method type ", HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
		return new ResponseEntity<>("The request for a resource that does not exist",
				HttpStatus.NOT_FOUND);
	} 
	
	@Override  
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)   
	{  
	//returning exception structure and specific status   
	return new ResponseEntity<>("Please Enter valid input"+ex.getBindingResult().toString(), HttpStatus.BAD_REQUEST);  
	}  
	
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<String> handleInternalServerError(InternalServerError internalServerError) {
		return new ResponseEntity<>("Server is down try after sometime!",
				HttpStatus.INTERNAL_SERVER_ERROR);
	} 
	
	
}
