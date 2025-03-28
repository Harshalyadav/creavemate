package com.cravemate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.cravemate.pojos.OrderFoodItems;



public interface OrderFoodItemsRepo extends JpaRepository<OrderFoodItems, Integer> {

}
