package com.cg.rp.demo.exception;

public class APIGatewayClientException extends APIGatewayBaseException {
	private static final long serialVersionUID = 1L;

	public APIGatewayClientException() {
	}

	public APIGatewayClientException(String message) {
		super(message);
	}

	public APIGatewayClientException(Throwable cause) {
		super(cause);
	}

	public APIGatewayClientException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public APIGatewayClientException(Integer statusCode, String reason) {
		super(statusCode, reason);
	}

	public APIGatewayClientException(Integer statusCode, String reason, Throwable cause) {
		super(statusCode, reason, cause);
	}
}
