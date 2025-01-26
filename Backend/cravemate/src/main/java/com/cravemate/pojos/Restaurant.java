package com.cravemate.pojos;

import java.time.LocalDateTime;
import java.util.List;

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

public class Restaurant extends BaseEntity {
	
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
      private Image menuImages;
      private Menu menu;
      
      @OneToMany
      private Review reviews;
      @OneToMany
      private Image photos;
      


}
