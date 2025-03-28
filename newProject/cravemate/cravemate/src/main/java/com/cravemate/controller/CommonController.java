package com.cravemate.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cravemate.pojos.FoodItem;
//import com.cravemate.pojos.RestaurantDetails;
//import com.cravemate.service.CommonService;
//
//@RestController
//@RequestMapping(value = "/cravemate")
//public class CommonController {
//    @Autowired
//    private CommonService commonService;
//
//    @GetMapping(value = "/get-restaurants")
//    public ResponseEntity<ArrayList<RestaurantDetails>> getRestaurants() {
//
//        return commonService.getRestaurants();
//
//    }
//
//    @PostMapping(value = "/get-fooditems")
//    public ResponseEntity<List<FoodItem>> getFoodItems(@RequestBody Map<String, Integer> entity) {
//
//        return commonService.getFoodItems(entity);
//
//    }
//
//}


 import com.cravemate.Dto.FoodItemDTO;
import com.cravemate.Dto.RestaurantDetailsDTO;
// Make sure you have the correct import for DTO
 // Make sure you have the correct import for DTO
import com.cravemate.service.CommonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cravemate")
public class CommonController {

    @Autowired
    private CommonService commonService;

    // Endpoint to get all restaurants (using RestaurantDetailsDTO)
    @GetMapping(value = "/get-restaurants")
    public ResponseEntity<List<RestaurantDetailsDTO>> getRestaurants() {
        return commonService.getRestaurants();
    }

    // Endpoint to get food items for a specific restaurant (using FoodItemDTO)
    @PostMapping(value = "/get-fooditems")
    public ResponseEntity<List<FoodItemDTO>> getFoodItems(@RequestBody Map<String, Integer> entity) {
        return commonService.getFoodItems(entity);
    }
}
