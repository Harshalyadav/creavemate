package com.orders.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orders.dto.OrderRequestDTO;
import com.orders.dto.OrderRespDTO;
import com.orders.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class FoodOrderController {  
    private  OrderService orderService;
    
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody 
    		OrderRequestDTO orderDetails){
        OrderRespDTO orderSavedInDB = orderService.saveOrderDetails(orderDetails);
        return ResponseEntity.status(HttpStatus.CREATED)
        		.body(orderSavedInDB);
    }




}
