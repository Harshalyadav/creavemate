package com.orders.service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.orders.dto.RestaurantDTO;

@FeignClient(name = "Restaurant-Service")
public interface RestaurantServiceClient {
	@GetMapping("/restaurants/{restaurantId}")
	public RestaurantDTO findRestaurantById(@PathVariable Long restaurantId);

}
