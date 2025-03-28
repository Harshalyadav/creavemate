package com.cravemate.pojos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "restaurant_info")
//public class RestaurantInfo {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "restaurant_id", unique = true, updatable = false, nullable = false)
//    private Integer restaurantid;
//
//    @Column(name = "restaurant_name", unique = false, updatable = true, nullable = false)
//    private String restaurantname;
//
//    @Column(name = "address", unique = false, updatable = true, nullable = false)
//    private String restaurantaddress;
//
//    @Column(name = "restaurant_rating", unique = false, updatable = true, nullable = true)
//    private Float restaurantrating = 0f;
//
//    @Column(name = "numofrating", unique = false, updatable = true, nullable = true)
//    private Integer numofrating = 0;
//
//    @JsonManagedReference
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantInfo")
//    private List<RestaurantImages> restaurantimages = new ArrayList<RestaurantImages>();
//
//    public Integer getNumofrating() {
//        return numofrating;
//    }
//
//    public void setNumofrating(Integer numofrating) {
//        this.numofrating = numofrating;
//    }
//
//    @JsonManagedReference
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantInfo")
//    private List<FoodItem> foodItem = new ArrayList<FoodItem>();
//
//    public Integer getRestaurantid() {
//        return restaurantid;
//    }
//
//    public void setRestaurantid(Integer restaurantid) {
//        this.restaurantid = restaurantid;
//    }
//
//    public String getRestaurantname() {
//        return restaurantname;
//    }
//
//    public void setRestaurantname(String restaurantname) {
//        this.restaurantname = restaurantname;
//    }
//
//    public String getRestaurantaddress() {
//        return restaurantaddress;
//    }
//
//    public void setRestaurantaddress(String restaurantaddress) {
//        this.restaurantaddress = restaurantaddress;
//    }
//
//    public List<RestaurantImages> getRestaurantimages() {
//        return restaurantimages;
//    }
//
//    public void setRestaurantimages(List<RestaurantImages> restaurantimages) {
//        this.restaurantimages = restaurantimages;
//    }
//
//    
//
//    public Float getRestaurantrating() {
//        return restaurantrating;
//    }
//
//    public void setRestaurantrating(Float rating) {
//        this.restaurantrating = rating;
//    }
//
//    public List<FoodItem> getFoodItem() {
//        return foodItem;
//    }
//
//    public void setFoodItem(List<FoodItem> foodItem) {
//        this.foodItem = foodItem;
//    }
//
//}
@Entity
@Table(name = "restaurant_info")
public class RestaurantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id", unique = true, updatable = false, nullable = false)
    private Integer restaurantid;

    @Column(name = "restaurant_name", unique = false, updatable = true, nullable = false)
    private String restaurantname;

    @Column(name = "address", unique = false, updatable = true, nullable = false)
    private String restaurantaddress;

    @Column(name = "restaurant_rating", unique = false, updatable = true, nullable = true)
    private Float restaurantrating = 0f;

    @Column(name = "numofrating", unique = false, updatable = true, nullable = true)
    private Integer numofrating = 0;

 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantInfo", fetch = FetchType.LAZY)
    private List<RestaurantImages> restaurantimages = new ArrayList<RestaurantImages>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantInfo", fetch = FetchType.LAZY)
    private List<FoodItem> foodItem = new ArrayList<FoodItem>();

    // Constructors
    public RestaurantInfo() {
        // Default constructor
    }

    public RestaurantInfo(Integer restaurantid, String restaurantname, String restaurantaddress, Float restaurantrating, Integer numofrating) {
        this.restaurantid = restaurantid;
        this.restaurantname = restaurantname;
        this.restaurantaddress = restaurantaddress;
        this.restaurantrating = restaurantrating;
        this.numofrating = numofrating;
    }

    // Getters and setters for all fields
    public Integer getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(Integer restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
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

    public Integer getNumofrating() {
        return numofrating;
    }

    public void setNumofrating(Integer numofrating) {
        this.numofrating = numofrating;
    }

    public List<RestaurantImages> getRestaurantimages() {
        return restaurantimages;
    }

    public void setRestaurantimages(List<RestaurantImages> restaurantimages) {
        this.restaurantimages = restaurantimages;
    }

    public List<FoodItem> getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(List<FoodItem> foodItem) {
        this.foodItem = foodItem;
    }
}

