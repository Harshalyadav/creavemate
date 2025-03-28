package com.cravemate.Dto;



import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RateOrderDTO {
    private Integer restaurantid;
    private String phonenumber;
    private Integer orderid;
    private Integer restaurantrating;
    private String restaurantreview;
    private List<FoodItemRatingDTO> foodItemRatings;

    // Getters and setters
@Getter
@Setter
    public static class FoodItemRatingDTO {
        private Integer fooditemid;
        private Double fooditemrating;
        private String fooditemreview;

        // Getters and setters
    }
}
