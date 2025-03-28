package com.orders.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter


public class OrderLineDTO {    
    private  int quantity;
    private  int subTotal;    
	private String  foodItemName;
}
