package com.cg.rp.demo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/tests")
public class TestController {

	@Value("${message}")
	public String message;
	
	@RequestMapping(value="/getMessage", method=RequestMethod.GET)
	public String testMessage() {
		System.out.println("Message from environment is " + message);
		return message;
	}
	
	@RequestMapping("/nullPointerExceptionHandler")  
    public String handleNullPointerException(){  
		// will be catched by controller exception hnadler handler method nfeHandler  
        throw new NullPointerException("Null Pointer Exception");  
    }
	
	@ExceptionHandler(value = NullPointerException.class)  
    public String nfeHandler(NumberFormatException e){  
        return e.getMessage();  
    }
}
