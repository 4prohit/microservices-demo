package com.cg.rp.demo.exception;

public class APIGatewayServerException extends APIGatewayBaseException {
	private static final long serialVersionUID = 1L;

	public APIGatewayServerException() {
	}

	public APIGatewayServerException(String message) {
		super(message);
	}

	public APIGatewayServerException(Throwable cause) {
		super(cause);
	}

	public APIGatewayServerException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public APIGatewayServerException(Integer statusCode, String reason) {
		super(statusCode, reason);
	}

	public APIGatewayServerException(Integer statusCode, String reason, Throwable cause) {
		super(statusCode, reason, cause);
	}
}
