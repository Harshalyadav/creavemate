package com.cravemate.Dto;



import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PlaceOrderDTO {
    private String phonenumber;
    private Integer restaurantid;
    private String restaurantname;
    private String deliveryaddress;
    private Integer totalamount;
    private List<OrderFoodItemDTO> foodItems;  // List of food items in the order

    // Getters and setters
}



