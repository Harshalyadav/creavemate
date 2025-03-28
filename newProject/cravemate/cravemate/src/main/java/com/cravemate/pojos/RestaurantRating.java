package com.cravemate.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant_rating")
public class RestaurantRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_rating_id", unique = true, updatable = false, nullable = false)
    private Integer restaurantratingid;

    @Column(name = "name", unique = false, updatable = true, nullable = false)
    private String name;

    @Column(name = "restaurant_id", unique = false, updatable = true, nullable = false)
    private Integer restaurantid;

    @Column(name = "restaurant_name", unique = false, updatable = true, nullable = false)
    private String restaurantname;

    @Column(name = "restaurant_rating", unique = false, updatable = true, nullable = false)
    private Float restaurantrating;

    @Column(name = "restaurant_review", unique = false, updatable = true, nullable = false)
    private String restaurantreview;

    public Integer getRestaurantratingid() {
        return restaurantratingid;
    }

    public void setRestaurantratingid(Integer restaurantratingid) {
        this.restaurantratingid = restaurantratingid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getRestaurantreview() {
        return restaurantreview;
    }

    public void setRestaurantreview(String restaurantreview) {
        this.restaurantreview = restaurantreview;
    }

    public Float getRestaurantrating() {
        return restaurantrating;
    }

    public void setRestaurantrating(Float float1) {
        this.restaurantrating = float1;
    }

}
