package com.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoodItemDTO {
    
    private String itemName;
    private String itemDescription;
    private boolean isVeg;
    private int price;
//    private int quantity;
    private Long restaurantId;
    
}
