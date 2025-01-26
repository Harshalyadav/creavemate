package com.cravemate.pojos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="review")
@NoArgsConstructor
@Getter
@Setter
public class Review {

	  private Food food;
	   private Restaurant restaurant;
	   private User user;
	   private String ratingText;
	   private boolean isRestaurantReview;
	   private boolean isFoodReivew;
	   private Image photos;
}
