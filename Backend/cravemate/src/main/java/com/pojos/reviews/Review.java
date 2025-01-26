package com.pojos.reviews;

import java.awt.Image;

import com.pojos.food.FoodPojo;
import com.pojos.restaurant.RestaurantPojo;
import com.pojos.user.User;

import jakarta.persistence.Entity;


@Entity
public class Review {
   private FoodPojo food;
   private RestaurantPojo restaurant;
   private User user;
   private String ratingText;
   private boolean isRestaurantReview;
   private boolean isFoodReivew;
   private Image photos;
}
