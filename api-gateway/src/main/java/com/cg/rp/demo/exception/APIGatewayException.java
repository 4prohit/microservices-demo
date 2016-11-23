package com.cg.rp.demo.exception;

public class APIGatewayException extends APIGatewayBaseException {
	private static final long serialVersionUID = 1L;

	public APIGatewayException() {
	}

	public APIGatewayException(String message) {
		super(message);
	}

	public APIGatewayException(Throwable cause) {
		super(cause);
	}

	public APIGatewayException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public APIGatewayException(Integer statusCode, String reason) {
		super(statusCode, reason);
	}

	public APIGatewayException(Integer statusCode, String reason, Throwable cause) {
		super(statusCode, reason, cause);
	}
}
