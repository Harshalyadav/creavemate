package com.cravemate.Dto;



public class FoodItemDTO {

    private Integer fooditemid;
    private String foodname;
    private String description;
    private Integer price;
    private String image;
    private Double fooditemrating;
    private Integer numofrating;
    private Integer restaurantid;  // Add this field to hold restaurant id

    // Constructor
    public FoodItemDTO(Integer fooditemid, String foodname, String description, Integer price, 
                        String image, Double fooditemrating, Integer numofrating, Integer restaurantid) {
        this.fooditemid = fooditemid;
        this.foodname = foodname;
        this.description = description;
        this.price = price;
        this.image = image;
        this.fooditemrating = fooditemrating;
        this.numofrating = numofrating;
        this.restaurantid = restaurantid;  // Initialize the restaurantid
    }

    // Getters and Setters
    public Integer getFooditemid() {
        return fooditemid;
    }

    public void setFooditemid(Integer fooditemid) {
        this.fooditemid = fooditemid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getFooditemrating() {
        return fooditemrating;
    }

    public void setFooditemrating(Double fooditemrating) {
        this.fooditemrating = fooditemrating;
    }

    public Integer getNumofrating() {
        return numofrating;
    }

    public void setNumofrating(Integer numofrating) {
        this.numofrating = numofrating;
    }

    public Integer getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(Integer restaurantid) {
        this.restaurantid = restaurantid;
    }
}
