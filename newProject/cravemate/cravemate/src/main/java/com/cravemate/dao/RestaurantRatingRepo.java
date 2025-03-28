package com.cravemate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cravemate.pojos.RestaurantRating;

public interface RestaurantRatingRepo extends JpaRepository<RestaurantRating, Integer> {

}
