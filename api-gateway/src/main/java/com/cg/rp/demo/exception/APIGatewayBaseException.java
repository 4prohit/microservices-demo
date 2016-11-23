package com.cg.rp.demo.exception;

public class APIGatewayBaseException extends Exception {
	private static final long serialVersionUID = 1L;

	private Integer statusCode;

	public APIGatewayBaseException() {
	}

	public APIGatewayBaseException(String message) {
		super(message);
	}

	public APIGatewayBaseException(Throwable cause) {
		super(cause);
	}

	public APIGatewayBaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public APIGatewayBaseException(Integer statusCode, String reason) {
		super(reason);
		this.statusCode = statusCode;
	}

	public APIGatewayBaseException(Integer statusCode, String reason, Throwable cause) {
		super(reason, cause);
		this.statusCode = statusCode;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

}
