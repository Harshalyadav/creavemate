package com.cravemate.controller;




import com.cravemate.Dto.FoodItemDTO;
import com.cravemate.Dto.RestaurantDeleteDTO;
import com.cravemate.Dto.RestaurantInfoDTO;
import com.cravemate.service.AdminService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cravemate/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Add Restaurant
    @PostMapping(value = "/add-restaurant")
    public ResponseEntity<String> addRestaurant(@RequestBody RestaurantInfoDTO restaurantInfoDTO) {
        return adminService.addRestaurant(restaurantInfoDTO);
    }

    // Edit Restaurant
    @PostMapping(value = "/edit-restaurant")
    public ResponseEntity<String> editRestaurant(@RequestBody RestaurantInfoDTO restaurantInfoDTO) {
        return adminService.editRestaurant(restaurantInfoDTO);
    }

    @PostMapping(value = "/delete-restaurant")
    public ResponseEntity<String> deleteRestaurant(@RequestBody Map<String, Integer> entity) {
        Integer restaurantId = entity.get("restaurantId");

        // Create and set up the DTO
        RestaurantDeleteDTO dto = new RestaurantDeleteDTO();
        dto.setRestaurantId(restaurantId);

        // Pass the DTO to the service method
        return adminService.deleteRestaurant(dto);
    }


    // Add Food Items
    @PostMapping(value = "/add-fooditems")
    public ResponseEntity<String> addFoodItems(@RequestBody FoodItemDTO foodItemDTO) {
        return adminService.addFoodItems(foodItemDTO);
    }

    // Edit Food Items
    @PostMapping(value = "/edit-fooditems")
    public ResponseEntity<String> editFoodItems(@RequestBody FoodItemDTO foodItemDTO) {
        return adminService.editFoodItems(foodItemDTO);
    }

    // Delete Food Item
    @PostMapping(value = "/delete-fooditem")
    public ResponseEntity<String> deleteFoodItem(@RequestBody Map<String, Integer> entity) {
        Integer foodItemId = entity.get("foodItemId");
        return adminService.deleteFoodItem(foodItemId);
    }
}
