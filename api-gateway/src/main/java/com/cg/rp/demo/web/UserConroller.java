package com.cg.rp.demo.web;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.rp.demo.domain.User;
import com.cg.rp.demo.exception.APIGatewayClientException;
import com.cg.rp.demo.exception.APIGatewayException;
import com.cg.rp.demo.service.client.UserServiceClient;

@RequestMapping("x-users")
@RestController
public class UserConroller {
	private final Logger logger = LoggerFactory.getLogger(UserConroller.class);
	
	private UserServiceClient userServiceClient;
	
	@Autowired
	public UserConroller(UserServiceClient userServiceClient) {
		this.userServiceClient = userServiceClient;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get() throws Throwable {
        logger.info("Getting all users");
        Collection<User> users = null;
        try {
        	users = userServiceClient.get().getContent();
		} catch (Exception e) {
			logger.error("Failed to get users. Error Message: " + e.getMessage());
			e.printStackTrace();
			throw new APIGatewayException(e.getMessage(), e.getCause());
		}
        if(null == users) {
        	logger.error("No users available");
            return new ResponseEntity<>("No users available", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if(users.isEmpty()) {
            logger.info("No users available");
            return new ResponseEntity<>("No users available", HttpStatus.NO_CONTENT);
        } else {
        	return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("userId") Long userId) throws Throwable {
        logger.info("Getting user with userId " + userId);
        User user = null;
        try {
        	user = userServiceClient.get(userId).getContent();
		} catch (Exception e) {
			logger.error("Failed to get user with userId " + userId + ". Error Message: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
        if(null == user) {
        	logger.error("No user available with userId " + userId + " not found");
            return new ResponseEntity<>("No user available with userId " + userId + " not found", HttpStatus.NOT_FOUND);
        }
        else {
        	return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody User newUser) throws Throwable {
		logger.info("Creating new user");
        User savedUser = null;
        if(null == newUser) {
        	logger.error("Invalid input to create User");
			throw new APIGatewayClientException(Integer.parseInt(HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        try {
        	savedUser = (userServiceClient.save(newUser)).getContent();
		} catch (Exception e) {
			logger.error("Failed to create user. Error Message: " + e.getMessage());
			e.printStackTrace();
			throw new APIGatewayException(e.getMessage(), e.getCause());
		}
        if(null == savedUser) {
        	logger.error("Failed to create user");
            return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
        	return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
    }
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("userId") Long userId, @RequestBody User newUser) throws Throwable {
		logger.info("Updating user with userId " + userId);
        User currentUser = null;
        User updatedUser = null;
        if(null == newUser) {
        	logger.error("Invalid input to update User");
			throw new APIGatewayClientException(Integer.parseInt(HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        try {
        	currentUser = userServiceClient.get(userId).getContent();
        	if(null == currentUser) {
            	logger.error("No user available with userId " + userId + " not found");
                return new ResponseEntity<>("No user available with userId " + userId + " not found", HttpStatus.NOT_FOUND);
            }
            else {
            	currentUser.setFirstName(newUser.getFirstName());
            	currentUser.setLastName(newUser.getLastName());
            	currentUser.setGender(newUser.getGender());
            	currentUser.setEmailId(newUser.getEmailId());
            }
		} catch (Exception e) {
			logger.error("Failed to get user with userId " + userId + ". Error Message: " + e.getMessage());
			e.printStackTrace();
			throw new APIGatewayException(e.getMessage(), e.getCause());
		}      
        try {
        	updatedUser = (userServiceClient.update(userId, currentUser)).getContent();
        	if(null == updatedUser) {
        		logger.error("Failed to update user");
                return new ResponseEntity<>("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else {
            	return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            }
		} catch (Exception e) {
			logger.error("Failed to update user. Error Message: " + e.getMessage());
			e.printStackTrace();
			throw new APIGatewayException(e.getMessage(), e.getCause());
		}
    }
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("userId") Long userId) throws Throwable {
		logger.info("Updating user with userId " + userId);
        User currentUser = null;
        try {
        	currentUser = userServiceClient.get(userId).getContent();
        	if(null == currentUser) {
            	logger.error("No user available with userId " + userId + " not found");
                return new ResponseEntity<>("No user available with userId " + userId + " not found", HttpStatus.NOT_FOUND);
            }
		} catch (Exception e) {
			logger.error("Failed to get user with userId " + userId + ". Error Message: " + e.getMessage());
			e.printStackTrace();
			throw new APIGatewayException(e.getMessage(), e.getCause());
		}      
        try {
        	userServiceClient.delete(userId);
        	return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed to delete user. Error Message: " + e.getMessage());
			e.printStackTrace();
			throw new APIGatewayException(e.getMessage(), e.getCause());
		}
    }

}
