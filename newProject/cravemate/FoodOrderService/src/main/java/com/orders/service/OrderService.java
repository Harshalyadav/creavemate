package com.orders.service;

import com.orders.dto.OrderRequestDTO;
import com.orders.dto.OrderRespDTO;

public interface OrderService {

	OrderRespDTO saveOrderDetails(OrderRequestDTO orderDetails);

}
