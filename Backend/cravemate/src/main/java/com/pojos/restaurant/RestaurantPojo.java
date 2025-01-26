package com.pojos.restaurant;

import java.time.LocalDateTime;
import java.util.List;

import com.pojos.BaseEntityPojo;
import com.pojos.image.Images;
import com.pojos.menu.MenuPojo;
import com.pojos.reviews.Review;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="restaurant")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper= true)

public class RestaurantPojo  extends BaseEntityPojo{
          private String name;
          private String city;
          private String address;
          private String mapLocation;
          private List<String> cuisine;
          private LocalDateTime restaurantTimings;
          private int contactNumber;
          private String website;
          private List<String> popularDishes;
          private int averageCost;
          private List<String> amenties;
          private Images menuImages;
          private MenuPojo menu;
          
          @OneToMany
          private Review reviews;
          @OneToMany
          private Images photos;
          
          
}


