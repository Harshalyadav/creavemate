package com.cravemate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cravemate.Dto.FoodItemDTO;
import com.cravemate.Dto.RestaurantDeleteDTO;
import com.cravemate.Dto.RestaurantImagesDTO;
import com.cravemate.Dto.RestaurantInfoDTO;
import com.cravemate.dao.FoodItemRepo;
import com.cravemate.dao.RestaurantImagesRepo;
import com.cravemate.dao.RestaurantInfoRepo;
import com.cravemate.pojos.FoodItem;
import com.cravemate.pojos.RestaurantImages;
import com.cravemate.pojos.RestaurantInfo;

@Service
public class AdminService {

    @Autowired
    private RestaurantInfoRepo restaurantInfoRepo;
    @Autowired
    private RestaurantImagesRepo restaurantImagesRepo;

    @Autowired
    private FoodItemRepo foodItemRepo;

    public ResponseEntity<String> addRestaurant(RestaurantInfoDTO restaurantInfoDTO) {
       
        Optional<RestaurantInfo> restaurant = restaurantInfoRepo
                .findByRestaurantnameAndRestaurantaddress(restaurantInfoDTO.getRestaurantName(),
                        restaurantInfoDTO.getAddress());

        if (restaurant.isPresent()) {
            return ResponseEntity.ok().body("address");
        }

        // Create a new RestaurantInfo object
        RestaurantInfo restaurantInfo = new RestaurantInfo();
        restaurantInfo.setRestaurantname(restaurantInfoDTO.getRestaurantName());
        restaurantInfo.setRestaurantaddress(restaurantInfoDTO.getAddress());

        // Process the images using RestaurantImagesDTO
        List<RestaurantImagesDTO> imageLinks = restaurantInfoDTO.getRestaurantImages(); // Assume RestaurantInfoDTO has getRestaurantImages()
        for (RestaurantImagesDTO imageDTO : imageLinks) {
            RestaurantImages img = new RestaurantImages();
            img.setLink(imageDTO.getLink());  // Set the image link
            img.setRestaurantInfo(restaurantInfo);  // Set the relation between image and restaurant
            restaurantInfo.getRestaurantimages().add(img);  // Add image to restaurant

            // Save the image to the repository (this saves the relation between restaurant and image)
            restaurantImagesRepo.save(img);
        }

        // Finally, save the restaurant information with its images
        restaurantInfoRepo.save(restaurantInfo);

        return ResponseEntity.ok().body("success");
    }
    
    
//    public ResponseEntity<String> editRestaurant(Map entity) {
//
//        Integer resturantid = (Integer) entity.get("restaurantid");
//        Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepo.findById(resturantid);
//        RestaurantInfo rest = restaurantInfo.get();
//
//        Optional<RestaurantInfo> info = restaurantInfoRepo.findByRestaurantnameAndRestaurantaddress(
//                (String) entity.get("restaurantname"), (String) entity.get("address"));
//        // if (info.isPresent()) {
//        // return ResponseEntity.ok().body("address");
//        // }
//
//        rest.setRestaurantname((String) entity.get("restaurantname"));
//        rest.setRestaurantaddress((String) entity.get("address"));
//        restaurantInfoRepo.save(rest);
//        restaurantImagesRepo.deleteByRestaurantid(resturantid); // native query written in class
//
//        ArrayList<String> imageLinks = (ArrayList) entity.get("restaurantimages");
//        ListIterator<String> ll = imageLinks.listIterator();
//        rest = restaurantInfo.get();
//        for (int i = 0; i < imageLinks.size(); i++) {
//            System.out.println("****************************************************" + imageLinks.get(i));
//        }
//
//        while (ll.hasNext()) {
//
//            RestaurantImages img = new RestaurantImages();
//            String link = ll.next();
//            img.setLink(link);
//            img.setRestaurantInfo(rest);
//            rest.getRestaurantimages().add(img);
//            restaurantInfoRepo.save(rest);
//        }
//        return ResponseEntity.ok().body("success");
//
//    }

    public ResponseEntity<String> editRestaurant(RestaurantInfoDTO restaurantInfoDTO) {
        Integer restaurantId = restaurantInfoDTO.getRestaurantId(); // Assuming RestaurantInfoDTO has restaurantId field

        // Retrieve the existing restaurant information
        Optional<RestaurantInfo> restaurantInfoOpt = restaurantInfoRepo.findById(restaurantId);
        if (!restaurantInfoOpt.isPresent()) {
            return ResponseEntity.status(404).body("Restaurant not found");
        }

        RestaurantInfo restaurantInfo = restaurantInfoOpt.get();
        
        // Check if there is another restaurant with the same name and address (to prevent duplicate)
        Optional<RestaurantInfo> existingRestaurant = restaurantInfoRepo
                .findByRestaurantnameAndRestaurantaddress(restaurantInfoDTO.getRestaurantName(),
                        restaurantInfoDTO.getAddress());

        if (existingRestaurant.isPresent() && !existingRestaurant.get().getRestaurantid().equals(restaurantId)) {
            return ResponseEntity.ok().body("address");
        }

        // Update restaurant fields
        restaurantInfo.setRestaurantname(restaurantInfoDTO.getRestaurantName());
        restaurantInfo.setRestaurantaddress(restaurantInfoDTO.getAddress());

        // Delete the old images related to the restaurant
        restaurantImagesRepo.deleteByRestaurantid(restaurantId);

        // Process the updated images
        List<RestaurantImagesDTO> imageLinks = restaurantInfoDTO.getRestaurantImages();
        for (RestaurantImagesDTO imageDTO : imageLinks) {
            RestaurantImages img = new RestaurantImages();
            img.setLink(imageDTO.getLink());  // Set the new image link
            img.setRestaurantInfo(restaurantInfo);  // Set the relationship between image and restaurant
            restaurantInfo.getRestaurantimages().add(img);  // Add the new image to restaurant's images

            // Save the image
            restaurantImagesRepo.save(img);
        }

        // Save the updated restaurant information
        restaurantInfoRepo.save(restaurantInfo);

        return ResponseEntity.ok().body("success");
    }
    
    
    
    
//    public ResponseEntity<String> deleteRestaurant(Map entity) {
//
//        restaurantInfoRepo.deleteById((Integer) entity.get("restaurantid"));
//        return ResponseEntity.ok().body("success");
//    }

    public ResponseEntity<String> deleteRestaurant(RestaurantDeleteDTO restaurantDeleteDTO) {
        Integer restaurantId = restaurantDeleteDTO.getRestaurantId(); // Get the restaurant ID from the DTO

        // Fetch the restaurant from the database
        Optional<RestaurantInfo> restaurantInfoOpt = restaurantInfoRepo.findById(restaurantId);
        if (!restaurantInfoOpt.isPresent()) {
            return ResponseEntity.status(404).body("Restaurant not found");
        }

        // Delete all images associated with the restaurant
        restaurantImagesRepo.deleteByRestaurantid(restaurantId);

        // Delete the restaurant
        restaurantInfoRepo.deleteById(restaurantId);

        return ResponseEntity.ok().body("success");
    }
    
    
//    public ResponseEntity<String> addFoodItems(Map entity) {
//
//        Optional<RestaurantInfo> restInfo = restaurantInfoRepo.findById((Integer) entity.get("restaurantid"));
//        Optional<FoodItem> fooditem = foodItemRepo.findByRestaurantidAndFoodname((Integer) entity.get("restaurantid"),
//                (String) entity.get("foodname"));
//
//        if (fooditem.isPresent()) {
//            return ResponseEntity.ok().body("name");
//        }
//        FoodItem fooditemInfo = new FoodItem();
//        fooditemInfo.setFoodname((String) entity.get("foodname"));
//        fooditemInfo.setDescription((String) entity.get("description"));
//        fooditemInfo.setImage((String) entity.get("image"));
//        fooditemInfo.setPrice(Integer.parseInt((String) entity.get("price")));
//        foodItemRepo.save(fooditemInfo);
//        fooditemInfo.setRestaurantInfo(restInfo.get());
//        restInfo.get().getFoodItem().add(fooditemInfo);
//        restaurantInfoRepo.save(restInfo.get());
//        return ResponseEntity.ok().body("success");
//
//    }


//    public ResponseEntity<String> addFoodItems(FoodItemDTO foodItemDTO) {
//
//        // Get the restaurant by ID
//        Optional<RestaurantInfo> restaurantInfoOpt = restaurantInfoRepo.findById(foodItemDTO.getRestaurantid());
//        if (!restaurantInfoOpt.isPresent()) {
//            return ResponseEntity.status(404).body("Restaurant not found");
//        }
//
//        RestaurantInfo restaurantInfo = restaurantInfoOpt.get();
//
//        // Check if food item already exists by name in the same restaurant
//        Optional<FoodItem> existingFoodItem = foodItemRepo.findByRestaurantidAndFoodname(
//                foodItemDTO.getRestaurantid(), foodItemDTO.getFoodname());
//
//        if (existingFoodItem.isPresent()) {
//            return ResponseEntity.ok().body("Food item with this name already exists in the restaurant");
//        }
//
//        // Convert price from String to Integer
//        Integer price = 0;
//        try {
//            price = Integer.parseInt(foodItemDTO.getPrice());
//        } catch (NumberFormatException e) {
//            return ResponseEntity.badRequest().body("Invalid price format");
//        }
//
//        // Create and populate the FoodItem entity from DTO
//        FoodItem foodItem = new FoodItem();
//        foodItem.setFoodname(foodItemDTO.getFoodname());
//        foodItem.setDescription(foodItemDTO.getDescription());
//        foodItem.setPrice(price);
//        foodItem.setImage(foodItemDTO.getImage());
//        foodItem.setFooditemrating(0.0);  // Default rating
//        foodItem.setNumofrating(0);      // Default number of ratings
//        foodItem.setRestaurantInfo(restaurantInfo);
//
//        // Save the food item
//        foodItemRepo.save(foodItem);
//
//        // Add the food item to the restaurant's list
//        restaurantInfo.getFoodItem().add(foodItem);
//        restaurantInfoRepo.save(restaurantInfo);
//
//        return ResponseEntity.ok().body("Food item added successfully");
//    }

    
    public ResponseEntity<String> addFoodItems(FoodItemDTO foodItemDTO) {

        // Get the restaurant by ID
        Optional<RestaurantInfo> restaurantInfoOpt = restaurantInfoRepo.findById(foodItemDTO.getRestaurantid());
        if (!restaurantInfoOpt.isPresent()) {
            return ResponseEntity.status(404).body("Restaurant not found");
        }

        RestaurantInfo restaurantInfo = restaurantInfoOpt.get();

        // Check if food item already exists by name in the same restaurant
        Optional<FoodItem> existingFoodItem = foodItemRepo.findByRestaurantidAndFoodname(
                foodItemDTO.getRestaurantid(), foodItemDTO.getFoodname());

        if (existingFoodItem.isPresent()) {
            return ResponseEntity.ok().body("Food item with this name already exists in the restaurant");
        }

        // Use the price directly as it is already an Integer
        Integer price = foodItemDTO.getPrice(); // No need for parsing anymore

        // Create and populate the FoodItem entity from DTO
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodname(foodItemDTO.getFoodname());
        foodItem.setDescription(foodItemDTO.getDescription());
        foodItem.setPrice(price);  // Set the price directly
        foodItem.setImage(foodItemDTO.getImage());
        foodItem.setFooditemrating(0.0);  // Default rating
        foodItem.setNumofrating(0);      // Default number of ratings
        foodItem.setRestaurantInfo(restaurantInfo);

        // Save the food item
        foodItemRepo.save(foodItem);

        // Add the food item to the restaurant's list
        restaurantInfo.getFoodItem().add(foodItem);
        restaurantInfoRepo.save(restaurantInfo);

        return ResponseEntity.ok().body("Food item added successfully");
    }

    
//    
//    public ResponseEntity<String> editFoodItems(Map entity) {
//
//        Integer resturantid = (Integer) entity.get("restaurantid");
//        Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepo.findById(resturantid);
//        RestaurantInfo rest = restaurantInfo.get();
//        Integer fooditemid = (Integer) entity.get("fooditemid");
//        Optional<FoodItem> fooditem1 = foodItemRepo.findByRestaurantidAndFoodname((Integer) entity.get("restaurantid"),
//                (String) entity.get("foodname"));
//
//        if (fooditem1.isPresent() && fooditem1.get().getFooditemid() != fooditemid) {
//            return ResponseEntity.ok().body("name");
//        }
//        Optional<FoodItem> fooditem = foodItemRepo.findById(fooditemid);
//        FoodItem f = fooditem.get();
//        f.setFoodname((String) entity.get("foodname"));
//        f.setDescription((String) entity.get("description"));
//        f.setImage((String) entity.get("image"));
//        f.setPrice(Integer.parseInt((String)entity.get("price")));
//        foodItemRepo.save(f);
//        f.setRestaurantInfo(rest);
//        restaurantInfoRepo.save(rest);
//        return ResponseEntity.ok().body("success");
//
//    }

    

//    public ResponseEntity<String> editFoodItems(Integer restaurantId, Integer foodItemId, FoodItemDTO foodItemDTO) {
//
//        // Get the restaurant by ID
//        Optional<RestaurantInfo> restaurantInfoOpt = restaurantInfoRepo.findById(restaurantId);
//        if (!restaurantInfoOpt.isPresent()) {
//            return ResponseEntity.status(404).body("Restaurant not found");
//        }
//
//        // Get the food item by ID
//        Optional<FoodItem> foodItemOpt = foodItemRepo.findById(foodItemId);
//        if (!foodItemOpt.isPresent()) {
//            return ResponseEntity.status(404).body("Food item not found");
//        }
//
//        // Get the existing food item
//        FoodItem foodItem = foodItemOpt.get();
//
//        // Check if there is already a food item with the same name (to prevent name conflicts)
//        Optional<FoodItem> existingFoodItem = foodItemRepo.findByRestaurantidAndFoodname(restaurantId, foodItemDTO.getFoodname());
//        if (existingFoodItem.isPresent() && !existingFoodItem.get().getFooditemid().equals(foodItemId)) {
//            return ResponseEntity.ok().body("Food item with this name already exists");
//        }
//
//        // Update food item properties
//        foodItem.setFoodname(foodItemDTO.getFoodname());
//        foodItem.setDescription(foodItemDTO.getDescription());
//        foodItem.setImage(foodItemDTO.getImage());
//        foodItem.setPrice(Integer.parseInt(foodItemDTO.getPrice()));
//
//        // Save the updated food item
//        foodItemRepo.save(foodItem);
//
//        return ResponseEntity.ok().body("Food item updated successfully");
//    }
    
    public ResponseEntity<String> editFoodItems(FoodItemDTO foodItemDTO) {

        // Fetch the food item by ID
        Optional<FoodItem> foodItemOpt = foodItemRepo.findById(foodItemDTO.getFooditemid());
        if (!foodItemOpt.isPresent()) {
            return ResponseEntity.status(404).body("Food item not found");
        }

        FoodItem foodItem = foodItemOpt.get();

        // Check if food item name already exists in the restaurant
        Optional<FoodItem> existingFoodItem = foodItemRepo.findByRestaurantidAndFoodname(
                foodItemDTO.getRestaurantid(), foodItemDTO.getFoodname());

        if (existingFoodItem.isPresent() && !existingFoodItem.get().getFooditemid().equals(foodItemDTO.getFooditemid())) {
            return ResponseEntity.ok().body("Food item with this name already exists in the restaurant");
        }

        // Update the food item
        foodItem.setFoodname(foodItemDTO.getFoodname());
        foodItem.setDescription(foodItemDTO.getDescription());

        // Convert price from String to Integer
        Integer price = 0;
        try {
            price = foodItemDTO.getPrice();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid price format");
        }
        foodItem.setPrice(price);
        foodItem.setImage(foodItemDTO.getImage());

        // Save the updated food item
        foodItemRepo.save(foodItem);

        return ResponseEntity.ok().body("Food item updated successfully");
    }


    

    public ResponseEntity<String> deleteFoodItem(Integer foodItemId) {

   
        Optional<FoodItem> foodItemOpt = foodItemRepo.findById(foodItemId);
        if (!foodItemOpt.isPresent()) {
            return ResponseEntity.status(404).body("Food item not found");
        }

  
        foodItemRepo.deleteById(foodItemId);

        return ResponseEntity.ok().body("Food item deleted successfully");
    }

}
