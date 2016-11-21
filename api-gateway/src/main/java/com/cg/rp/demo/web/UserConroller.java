package com.cg.rp.demo.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.rp.demo.domain.User;
import com.cg.rp.demo.service.client.UserServiceClient;

@RequestMapping("x-users")
@RestController
public class UserConroller {
	
	private UserServiceClient userServiceClient;
	
	@Autowired
	public UserConroller(UserServiceClient userServiceClient) {
		this.userServiceClient = userServiceClient;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity get() {
        System.out.println("Getting all users");
        Collection<User> users = userServiceClient.get().getContent();
        if (null == users) {
            System.out.println("No employees available");
            return new ResponseEntity("No users available", HttpStatus.NO_CONTENT);
        }
        else {
        	return new ResponseEntity(users, HttpStatus.OK);
        }
    }

}
