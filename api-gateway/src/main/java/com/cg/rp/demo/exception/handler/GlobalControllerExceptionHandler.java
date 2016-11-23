package com.cg.rp.demo.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.rp.demo.exception.APIGatewayBaseException;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ExceptionHandler(Throwable.class)
	ResponseEntity<?> handleThrowable(HttpServletRequest request, Throwable ex) {
		HttpStatus status = getStatus(request, ex);
		String errorCause = null;
		if(null != ex.getCause())
			errorCause = ex.getCause().getMessage();
		if(null == errorCause)
			errorCause = status.getReasonPhrase();
		return new ResponseEntity<>(new CustomErrorType(status.value(), ex.getMessage(), status.getReasonPhrase(), errorCause), status);
	}

	private HttpStatus getStatus(HttpServletRequest request, Throwable ex) {
		Integer statusCode = null;
		if (ex instanceof APIGatewayBaseException && null != ((APIGatewayBaseException) ex).getStatusCode()) {
			statusCode = ((APIGatewayBaseException) ex).getStatusCode();
		} else if (ex.getCause() instanceof APIGatewayBaseException && null != ((APIGatewayBaseException) ex.getCause()).getStatusCode()) {
			statusCode = ((APIGatewayBaseException) ex.getCause()).getStatusCode();
		} else {
			statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");			
		}
		if (null == statusCode) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}

}
