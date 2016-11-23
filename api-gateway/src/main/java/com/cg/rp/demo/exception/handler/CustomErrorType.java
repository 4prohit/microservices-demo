package com.cg.rp.demo.exception.handler;

public class CustomErrorType {
	private Integer statusCode;
	private String errorMessage;
	private String errorDescription;
	private String errorCause;
	
	public CustomErrorType(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public CustomErrorType(Integer statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
	}
	
	public CustomErrorType(Integer statusCode, String errorMessage, String errorDescription) {
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.errorDescription = errorDescription;
	}

	public CustomErrorType(Integer statusCode, String errorMessage, String errorDescription, String errorCause) {
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.errorDescription = errorDescription;
		this.errorCause = errorCause;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getErrorCause() {
		return errorCause;
	}

	public void setErrorCause(String errorCause) {
		this.errorCause = errorCause;
	}

}
