package com.cravemate.Dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class RestaurantInfoDTO {

    private Integer restaurantId;
    private String restaurantName;
    private String address;
    private Float restaurantRating;
    private Integer numOfRating;
    private List<RestaurantImagesDTO> restaurantImages;

    private List<String> foodItems; // Could be just food item names for simplicity

    // Getters and Setters

}
