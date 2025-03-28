package com.cravemate.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cravemate.pojos.FoodItemRating;

public interface FoodItemRatingRepo extends JpaRepository<FoodItemRating, Integer> {

}
