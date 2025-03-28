package com.orders.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {
	@NotEmpty
    private List<FoodOrderItem> foodOrderItems;
	@NotNull
    private Long customerId;
	@NotNull
    private Long restaurantId;
	@NotNull
    private DeliveryAddressDTO deliveryAddress;
   
}
