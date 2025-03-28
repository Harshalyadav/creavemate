//package com.orders.service.clients;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.orders.dto.UserRespDTO;
//
//@FeignClient(name="User-Service")
//public interface UserServiceClient {
//	@GetMapping("/users/{userId}")
//	public UserRespDTO getUserDetails(@PathVariable Long userId);
//}
