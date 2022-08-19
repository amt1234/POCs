package com.microservice.patient.exception;

import org.springframework.stereotype.Component;

@Component
public class EmptyInputFieldException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8861445784859553619L;

	private final String errorCode;
	private final String errorMsg;

	public String getErrorCode() {
		return errorCode;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


	public EmptyInputFieldException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public EmptyInputFieldException() {
		this.errorCode = "";
		this.errorMsg = "";

	}

}
