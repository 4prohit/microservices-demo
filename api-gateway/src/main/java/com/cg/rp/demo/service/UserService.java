package com.cg.rp.demo.service;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cg.rp.demo.domain.User;

public interface UserService {
	
	@RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = "application/json")
	Resources<User> get();
	
	@RequestMapping(value = "/api/users/{userId}", method = RequestMethod.GET, produces = "application/json")
	Resource<User> get(@PathVariable("userId") int userId);
	
	@RequestMapping(value = "/api/users/{userId}", method = RequestMethod.POST, produces = "application/json")
	Resource<User> save(@RequestBody User user);
	
	@RequestMapping(value = "/api/users/{userId}", method = RequestMethod.POST, produces = "application/json")
	Resource<User> update(@PathVariable("userId") int userId, @RequestBody User user);
	
	@RequestMapping(value = "/api/users/{userId}", method = RequestMethod.DELETE, produces = "application/json")
	void delete(@PathVariable("userId") int userId);
	
}
