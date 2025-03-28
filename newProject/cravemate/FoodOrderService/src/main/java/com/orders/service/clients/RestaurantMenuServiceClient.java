package com.orders.service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.orders.dto.FoodItemDTO;

@FeignClient(name="Restaurant-Menu-Service")
public interface RestaurantMenuServiceClient {
	 @GetMapping("/menu/food_item/{foodItemId}")
	  FoodItemDTO fetchFoodItemDetails(@PathVariable Long foodItemId);
}
