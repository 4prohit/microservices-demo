package com.cg.rp.demo.exception.handler;

import org.springframework.stereotype.Component;

import com.cg.rp.demo.exception.APIGatewayClientException;
import com.cg.rp.demo.exception.APIGatewayServerException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignClientErrorDecoder implements ErrorDecoder {
	private final ErrorDecoder defaultErrorDecoder = new Default();
	
	@Override
	public Exception decode(String methodKey, Response response) {
		if (response.status() >= 400 && response.status() <= 499) {
			return new APIGatewayClientException(response.status(), response.reason());
		}
		if (response.status() >= 500 && response.status() <= 599) {
            return new APIGatewayServerException(response.status(), response.reason());
        }
		return defaultErrorDecoder.decode(methodKey, response);
	}

}
