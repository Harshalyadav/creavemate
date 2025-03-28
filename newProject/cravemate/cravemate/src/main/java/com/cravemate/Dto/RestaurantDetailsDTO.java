package com.cravemate.Dto;





import java.util.List;

public class RestaurantDetailsDTO {

    private String restaurantname;
    private Integer restaurantid;
    private String restaurantaddress;
    private Float restaurantrating;
    private List<String> restaurantimages;  // List of image URLs

    public RestaurantDetailsDTO(String restaurantname, Integer restaurantid, String restaurantaddress,
                                 Float restaurantrating, List<String> restaurantimages) {
        this.restaurantname = restaurantname;
        this.restaurantid = restaurantid;
        this.restaurantaddress = restaurantaddress;
        this.restaurantrating = restaurantrating;
        this.restaurantimages = restaurantimages;
    }

    // Getters and setters
    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public Integer getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(Integer restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getRestaurantaddress() {
        return restaurantaddress;
    }

    public void setRestaurantaddress(String restaurantaddress) {
        this.restaurantaddress = restaurantaddress;
    }

    public Float getRestaurantrating() {
        return restaurantrating;
    }

    public void setRestaurantrating(Float restaurantrating) {
        this.restaurantrating = restaurantrating;
    }

    public List<String> getRestaurantimages() {
        return restaurantimages;
    }

    public void setRestaurantimages(List<String> restaurantimages) {
        this.restaurantimages = restaurantimages;
    }
}
