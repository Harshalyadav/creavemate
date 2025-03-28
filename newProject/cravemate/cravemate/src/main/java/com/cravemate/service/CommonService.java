package com.cravemate.service;

//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.cravemate.dao.RestaurantInfoRepo;
//import com.cravemate.pojos.FoodItem;
//import com.cravemate.pojos.RestaurantDetails;
//import com.cravemate.pojos.RestaurantInfo;
//
//@Service
//public class CommonService {
//
//    @Autowired
//    private RestaurantInfoRepo restaurantInfoRepo;
//
//    public ResponseEntity<ArrayList<RestaurantDetails>> getRestaurants() {
//
//        Iterable<RestaurantInfo> restaurants = restaurantInfoRepo.findAll();
//        Iterator<RestaurantInfo> i = restaurants.iterator();
//
//        ArrayList<RestaurantDetails> restInf = new ArrayList<RestaurantDetails>();
//
//        while (i.hasNext()) {
//            RestaurantInfo r = i.next();
//            RestaurantDetails ra = new RestaurantDetails(r.getRestaurantname(), r.getRestaurantid(),
//                    r.getRestaurantaddress(), r.getRestaurantimages(),r.getRestaurantrating());
//
//            restInf.add(ra);
//
//        }
//        return ResponseEntity.ok().body(restInf);
//    }
//
//    // for specific restaurant
//    public ResponseEntity<List<FoodItem>> getFoodItems(Map<String, Integer> entity) {
//
//        Optional<RestaurantInfo> restInfo = restaurantInfoRepo.findById(entity.get("restaurantid"));
//
//        RestaurantInfo restaurantInfo = restInfo.get();
//        List<FoodItem> foodItem = restaurantInfo.getFoodItem();
//        return ResponseEntity.ok().body(foodItem);
//
//    }
//}


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cravemate.Dto.FoodItemDTO;
import com.cravemate.Dto.RestaurantDetailsDTO;
import com.cravemate.dao.RestaurantInfoRepo;
import com.cravemate.pojos.FoodItem;
import com.cravemate.pojos.RestaurantImages;
import com.cravemate.pojos.RestaurantInfo;

@Service
public class CommonService {

    @Autowired
    private RestaurantInfoRepo restaurantInfoRepo;

    public ResponseEntity<List<RestaurantDetailsDTO>> getRestaurants() {

        Iterable<RestaurantInfo> restaurants = restaurantInfoRepo.findAll();
        Iterator<RestaurantInfo> i = restaurants.iterator();

        List<RestaurantDetailsDTO> restInf = new ArrayList<>();

        while (i.hasNext()) {
            RestaurantInfo r = i.next();

            // Extract only the image links (URLs)
            List<String> imageLinks = new ArrayList<>();
            for (RestaurantImages image : r.getRestaurantimages()) {
                imageLinks.add(image.getLink());  // Get the image URL from the RestaurantImages entity
            }

            // Construct RestaurantDetailsDTO with the extracted image links
            RestaurantDetailsDTO ra = new RestaurantDetailsDTO(
                    r.getRestaurantname(),
                    r.getRestaurantid(),
                    r.getRestaurantaddress(),
                    r.getRestaurantrating(),
                    imageLinks
            );

            restInf.add(ra);
        }

        return ResponseEntity.ok().body(restInf);
    }



//    // Method to get food items for a specific restaurant
//    public ResponseEntity<List<FoodItem>> getFoodItems(Map<String, Integer> entity) {
//
//        Optional<RestaurantInfo> restInfo = restaurantInfoRepo.findById(entity.get("restaurantid"));
//
//        // Handle the case where the restaurant ID is not found
//        if (restInfo.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        RestaurantInfo restaurantInfo = restInfo.get();
//        List<FoodItem> foodItem = restaurantInfo.getFoodItem();
//        return ResponseEntity.ok().body(foodItem);
//    }
    
    public ResponseEntity<List<FoodItemDTO>> getFoodItems(Map<String, Integer> entity) {
        Optional<RestaurantInfo> restInfo = restaurantInfoRepo.findById(entity.get("restaurantid"));

        if (restInfo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        RestaurantInfo restaurantInfo = restInfo.get();
        List<FoodItem> foodItems = restaurantInfo.getFoodItem();

        // Convert FoodItem entities to DTOs, and set restaurantid
        List<FoodItemDTO> foodItemDTOs = foodItems.stream()
            .map(foodItem -> new FoodItemDTO(
                    foodItem.getFooditemid(),
                    foodItem.getFoodname(),
                    foodItem.getDescription(),
                    foodItem.getPrice(),
                    foodItem.getImage(),
                    foodItem.getFooditemrating(),
                    foodItem.getNumofrating(),
                    restaurantInfo.getRestaurantid()  // Set the restaurant id
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok().body(foodItemDTOs);
    }

}

