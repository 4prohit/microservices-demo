package com.cg.rp.demo.service.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.cg.rp.demo.service.UserService;

@FeignClient(value = "user-service")
public interface UserServiceClient extends UserService {

}
