package com.orders.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.orders.entities.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRespDTO {

    private Long id;
    
    private OrderStatus orderStatus;	

	private int orderAmount;
	
	private LocalDateTime orderDateTime;	
	
	private LocalDateTime deliveryDateTime;	

	private int deliveryCharges;

    private List<OrderLineDTO> orderLineList;
    private String  restaurantName;
    private String customerName;
}
