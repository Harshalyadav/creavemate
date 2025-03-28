package com.cravemate.controller;

//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cravemate.pojos.FooditemDetails;
//import com.cravemate.pojos.OrderInfo;
//import com.cravemate.pojos.RestaurantInfo;
//import com.cravemate.pojos.SearchFoodItem;
//import com.cravemate.service.UserService;
//import com.cravemate.service.ValidUser;
//
//@RestController
//@RequestMapping(value = "/cravemate/user")
//public class UserControler {
//
//    @Autowired
//    private ValidUser validUser;
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/signup")
//    ResponseEntity<String> signup(@RequestBody Map<String, String> signupDetails) {
//        if (!validUser.isPhoneNumberUnique(signupDetails.get("phonenumber"))) {
//            return new ResponseEntity<>("phone", HttpStatus.OK);
//        }
//
//        return userService.signUp(signupDetails);
//
//    }
//
//    @PostMapping(value = "/login")
//    public ResponseEntity<String> login(@RequestBody Map<String, String> login) {
//        if (validUser.isPhoneNumberUnique(login.get("phonenumber"))) {
//            return new ResponseEntity<>("phone", HttpStatus.OK);
//        }
//        if (!validUser.isPasswordValid(login.get("phonenumber"), login.get("password"))) {
//            return new ResponseEntity<>("password", HttpStatus.OK);
//
//        }
//
//        return userService.login(login);
//    }
//
//    @PostMapping(value = "/logout")
//    public ResponseEntity<String> logout(@RequestBody Map entity) {
//        return userService.logout(entity);
//    }
//
//    @PostMapping(value = "/forgot-password")
//    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> entity) {
//        if (validUser.isPhoneNumberUnique(entity.get("phonenumber"))) {
//            return new ResponseEntity<>("phone", HttpStatus.OK);
//        }
//        return userService.forgotPassword(entity);
//
//    }
//
//    @PostMapping(value = "/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> entity) {
//
//        return userService.resetPassword(entity);
//
//    }
//
//    @PostMapping(value = "/search-by-name")
//    public ResponseEntity<List<RestaurantInfo>> searchByName(@RequestBody Map<String, String> entity) {
//
//        return userService.searchByName(entity);
//    }
//
//    @PostMapping(value = "/search-by-fooditem")
//    public ResponseEntity<List<SearchFoodItem>> searchByFoodItem(@RequestBody Map<String, String> entity) {
//
//        return userService.searchByFoodItem(entity);
//    }
//
//    @PostMapping(value = "/place-order")
//    public ResponseEntity<String> placeOrder(@RequestBody Map entity) {
//
//        return userService.placeOrder(entity);
//    }
//
//    @PostMapping(value = "/rate-order")
//    public ResponseEntity<String> rateOrder(@RequestBody Map entity) {
//        return userService.rateOrder(entity);
//
//    }
//
//    @GetMapping(value = "/get-all-food-items")
//    public ResponseEntity<List<FooditemDetails>> getFoodAllItems() {
//
//        return userService.getAllFoodItems();
//
//    }
//    @PostMapping(value = "/get-all-order-details")
//    public ResponseEntity<List<OrderInfo>> getAllOrderDetails(@RequestBody Map entity) {
//
//        return userService.getAllOrderDetails(entity);
//    }
//
//}


import com.cravemate.Dto.PlaceOrderDTO;
import com.cravemate.Dto.RateOrderDTO;
import com.cravemate.Dto.UserInfoDTO;
import com.cravemate.Dto.UserInfoUpdateDTO;
import com.cravemate.pojos.OrderInfo;
import com.cravemate.pojos.RestaurantInfo;
import com.cravemate.pojos.FooditemDetails;
import com.cravemate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // User signup
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserInfoDTO userInfoDTO) {
    
        return userService.signUp(userInfoDTO);
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginDetails) {
    	System.out.println("userInfoDTO"+loginDetails);
        return userService.login(loginDetails);
    }

    // User logout
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody Map<String, String> entity) {
        return userService.logout(entity);
    }

    // Forgot password
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody UserInfoDTO userInfoDTO) {
        return userService.forgotPassword(userInfoDTO);
    }

    // Reset password
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody UserInfoUpdateDTO userInfoUpdateDTO) {
        return userService.resetPassword(userInfoUpdateDTO);
    }

    // Search restaurant by name
    @GetMapping("/search-restaurant")
    public ResponseEntity<List<RestaurantInfo>> searchByName(@RequestParam Map<String, String> entity) {
        return userService.searchByName(entity);
    }

    // Search food items
    @GetMapping("/search-food-item")
    public ResponseEntity<List<FooditemDetails>> searchByFoodItem(@RequestParam Map<String, String> entity) {
        return userService.searchByFoodItem(entity);
    }

    // Place an order
    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        return userService.placeOrder(placeOrderDTO);
    }

    // Rate an order
    @PostMapping("/rate-order")
    public ResponseEntity<String> rateOrder(@RequestBody RateOrderDTO rateOrderDTO) {
        return userService.rateOrder(rateOrderDTO);
    }

    // Get all food items
    @GetMapping("/all-food-items")
    public ResponseEntity<List<FooditemDetails>> getAllFoodItems() {
        return userService.getAllFoodItems();
    }

    // Get all orders of a user
    @GetMapping("/all-orders")
    public ResponseEntity<List<OrderInfo>> getAllOrderDetails(@RequestParam Map<String, String> entity) {
        return userService.getAllOrderDetails(entity);
    }
}

