package com.cravemate.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cravemate.Dto.OrderFoodItemDTO;
import com.cravemate.Dto.PlaceOrderDTO;
import com.cravemate.Dto.RateOrderDTO;
import com.cravemate.Dto.UserInfoDTO;
import com.cravemate.Dto.UserInfoUpdateDTO;
import com.cravemate.dao.FoodItemRatingRepo;
import com.cravemate.dao.FoodItemRepo;
import com.cravemate.dao.OrderFoodItemsRepo;
import com.cravemate.dao.OrderInfoRepo;
import com.cravemate.dao.RestaurantInfoRepo;
import com.cravemate.dao.RestaurantRatingRepo;
import com.cravemate.dao.UserInfoRepo;
import com.cravemate.pojos.FoodItem;
import com.cravemate.pojos.FoodItemRating;
import com.cravemate.pojos.FooditemDetails;
import com.cravemate.pojos.OrderFoodItems;
import com.cravemate.pojos.OrderInfo;
import com.cravemate.pojos.RestaurantInfo;
import com.cravemate.pojos.RestaurantRating;
import com.cravemate.pojos.SearchFoodItem;
import com.cravemate.pojos.UserInfo;
import com.cravemate.utils.PasswordUtil;

@Service
public class UserService {
    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private RestaurantInfoRepo restaurantInfoRepo;

    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    private OrderInfoRepo orderInfoRepo;

    @Autowired
    private RestaurantRatingRepo restaurantRatingRepo;
    @Autowired
    private FoodItemRatingRepo foodItemRatingRepo;

    public ResponseEntity<String> signUp(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(userInfoDTO.getName());
        userInfo.setPhonenumber(userInfoDTO.getPhonenumber());
        userInfo.setSecretquestion(userInfoDTO.getSecretquestion());
        userInfo.setAddress(userInfoDTO.getAddress());
        userInfo.setAnswer(userInfoDTO.getAnswer());

        userInfo.setPassword(PasswordUtil.hashPassword(userInfoDTO.getPassword()));

        userInfo.setLoginstatus(Boolean.FALSE);

        userInfo = userInfoRepo.save(userInfo);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public ResponseEntity<String> login(Map<String, String> login) {
        Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber(login.get("phonenumber"));
    	System.out.println("userInfo"+login.get("phonenumber"));
        if (userInfo.isPresent()) {
            UserInfo userInfo1 = userInfo.get();
            System.out.println("userInfo1"+PasswordUtil.verifyPassword(login.get("password"), userInfo1.getPassword()));
            if (PasswordUtil.verifyPassword(login.get("password"), userInfo1.getPassword())) {
                userInfo1.setLoginstatus(Boolean.TRUE);
                userInfoRepo.save(userInfo1);

                if (userInfo1.getRole() == 0) {
                    return new ResponseEntity<>("success_admin", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("success_user", HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>("invalid_credentials", HttpStatus.UNAUTHORIZED);
    }


    public ResponseEntity<String> logout(Map entity) {
        Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber((String) entity.get("phonenumber"));
        UserInfo userInfo1 = userInfo.get();
        userInfo1.setLoginstatus(Boolean.FALSE);
        userInfo1 = userInfoRepo.save(userInfo1);
        return ResponseEntity.ok().body("success");
    }

    public ResponseEntity<String> forgotPassword(UserInfoDTO userInfoDTO) {
        Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber(userInfoDTO.getPhonenumber());
        if (userInfo.isPresent()) {
            UserInfo userInfo1 = userInfo.get();
            return new ResponseEntity<>(userInfo1.getSecretquestion(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }



    public ResponseEntity<String> resetPassword(UserInfoUpdateDTO userInfoUpdateDTO) {
        Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber(userInfoUpdateDTO.getPhonenumber());
        if (userInfo.isPresent()) {
            UserInfo userInfo1 = userInfo.get();

            if (userInfo1.getSecretquestion().equals(userInfoUpdateDTO.getSecretquestion())) {
                if (userInfo1.getAnswer().equals(userInfoUpdateDTO.getAnswer())) {
                    String hashedPassword = PasswordUtil.hashPassword(userInfoUpdateDTO.getNewpassword());
                    userInfo1.setPassword(hashedPassword);
                    userInfoRepo.save(userInfo1);
                    return new ResponseEntity<>("Password reset successful", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Incorrect answer to secret question", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Secret question mismatch", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<List<RestaurantInfo>> searchByName(Map<String, String> entity) {

        String search = entity.get("search");
        String[] words = search.split(" ");

        ArrayList<RestaurantInfo> common = new ArrayList<RestaurantInfo>();

        for (int i = 0; i < words.length; i++) {

            if (words[i] == "") {
                continue;
            }
            common.addAll(restaurantInfoRepo.findByRestaurantnameContaining(words[i],
                    Sort.by(Sort.Direction.DESC, "restaurantrating")));
        }

        Set<RestaurantInfo> set = new LinkedHashSet<RestaurantInfo>(common);
        List<RestaurantInfo> restaurant = new ArrayList<RestaurantInfo>(set);

        return ResponseEntity.ok().body(restaurant);
    }

    // public ResponseEntity<List<RestaurantInfo>> searchByName(Map<String, String>
    // entity) {

    // List<RestaurantInfo> restaurants =
    // restaurantInfoRepo.searchRestaurantName(entity.get("search"));
    // return ResponseEntity.ok().body(restaurants);

    // }
    // Helper method to map SearchFoodItem to FooditemDetails
 // Helper method to map SearchFoodItem to FooditemDetails
    private FooditemDetails mapToFooditemDetails(SearchFoodItem searchFoodItem) {
        FooditemDetails fooditemDetails = new FooditemDetails();
        fooditemDetails.setRestaurantid(searchFoodItem.getRestaurantid());
        fooditemDetails.setRestaurantname(searchFoodItem.getRestaurantname());
        fooditemDetails.setFoodItem(searchFoodItem.getFoodItem());
        return fooditemDetails;
    }
    public ResponseEntity<List<FooditemDetails>> searchByFoodItem(Map<String, String> entity) {
        String searchKeyword = entity.get("foodItem");

        List<FoodItem> foodItems = foodItemRepo.findByFoodnameContaining(searchKeyword);

        // Convert FoodItem objects to SearchFoodItem (if required)
        List<SearchFoodItem> searchFoodItems = foodItems.stream()
            .map(foodItem -> {
                RestaurantInfo rest = foodItem.getRestaurantInfo();  // Access RestaurantInfo from FoodItem
                return new SearchFoodItem(
                    rest.getRestaurantid(),    // Assuming RestaurantInfo has getRestaurantId()
                    rest.getRestaurantname(),  // Assuming RestaurantInfo has getRestaurantName()
                    rest.getRestaurantaddress(), // Assuming RestaurantInfo has getRestaurantAddress()
                    rest.getRestaurantrating(), // Assuming RestaurantInfo has getRestaurantRating()
                    foodItem
                );
            })
            .collect(Collectors.toList());

        // Map SearchFoodItem to FooditemDetails
        List<FooditemDetails> fooditemDetailsList = searchFoodItems.stream()
            .map(this::mapToFooditemDetails)
            .collect(Collectors.toList());

        return ResponseEntity.ok(fooditemDetailsList);  // Return the mapped list as a response
    }


//    public ResponseEntity<String> placeOrder(Map entity) {
//
//        Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepo.findById((Integer) entity.get("restaurantid"));
//        RestaurantInfo rest = restaurantInfo.get();
//        OrderInfo orderInfo = new OrderInfo();
//        orderInfo.setRestaurantid((Integer) entity.get("restaurantid"));
//        orderInfo.setRestaurantname((String) entity.get("restaurantname"));
//        Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber((String) entity.get("phonenumber"));
//        UserInfo user = userInfo.get();
//        orderInfo.setUserid(user.getUserid());
//        orderInfo.setDeliveryaddress((String) entity.get("deliveryaddress"));
//        orderInfo.setTotalamount((Integer) entity.get("totalamount"));
//        orderInfoRepo.save(orderInfo);
//
//        ArrayList<String> fooditemid = (ArrayList) entity.get("fooditemid");
//        ListIterator<String> ll = fooditemid.listIterator();
//
//        ArrayList<String> foodname = (ArrayList) entity.get("foodname");
//        ListIterator<String> name = foodname.listIterator();
//
//        ArrayList<String> amount = (ArrayList) entity.get("amount");
//        ListIterator<String> fAmount = amount.listIterator();
//
//        ArrayList<String> quantity = (ArrayList) entity.get("quantity");
//        ListIterator<String> qua = quantity.listIterator();
//
//        while (ll.hasNext()) {
//
//            OrderFoodItems orderFoodItems = new OrderFoodItems();
//
//            String s = ll.next();
//            orderFoodItems.setFooditemid(Integer.parseInt(s));
//
//            s = name.next();
//
//            orderFoodItems.setFoodname(s);
//
//            s = fAmount.next();
//            orderFoodItems.setAmount(Integer.parseInt(s));
//
//            s = qua.next();
//            orderFoodItems.setQuantity(Integer.parseInt(s));
//
//            orderFoodItems.setOrderinfo(orderInfo);
//            orderInfo.getOrderFoodItems().add(orderFoodItems);
//            orderInfoRepo.save(orderInfo);
//
//        }
//
//        // ger keys = (Integer) entity.get("keycount");
//
//        // (Integer i = 1; i <= keys; i++) {
//        // String s = i.toString();
//        // // Map<OrderFoodItems> map = (Map<OrderFoodItems>)entity.get(s);
//        // List<Map> list = (ArrayList<Map>) entity.get(s);
//        // ator<Map> itr = list.iterator();
//
//        // e (itr.hasNext()) {
//        // map = itr.next();
//        // Set<Entry> entrySet = map.entrySet();
//        // for(Entry entry : entrySet){
//        // entry.get("fooditemid")
//        // }
//
//        // }
//
//        // }
//        System.out.println("*******************************" + orderInfo);
//
//        return ResponseEntity.ok().body("success");
//    }
    public ResponseEntity<String> placeOrder(PlaceOrderDTO placeOrderDTO) {
        Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepo.findById(placeOrderDTO.getRestaurantid());
        if (!restaurantInfo.isPresent()) {
            return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
        }
        RestaurantInfo rest = restaurantInfo.get();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setRestaurantid(placeOrderDTO.getRestaurantid());
        orderInfo.setRestaurantname(placeOrderDTO.getRestaurantname());
        Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber(placeOrderDTO.getPhonenumber());
        if (!userInfo.isPresent()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        UserInfo user = userInfo.get();
        orderInfo.setUserid(user.getUserid());
        orderInfo.setDeliveryaddress(placeOrderDTO.getDeliveryaddress());
        orderInfo.setTotalamount(placeOrderDTO.getTotalamount());

        orderInfoRepo.save(orderInfo);

        for (OrderFoodItemDTO foodItemDTO : placeOrderDTO.getFoodItems()) {
            OrderFoodItems orderFoodItems = new OrderFoodItems();
            orderFoodItems.setFooditemid(foodItemDTO.getFooditemid());
            orderFoodItems.setFoodname(foodItemDTO.getFoodname());
            orderFoodItems.setAmount(foodItemDTO.getAmount());
            orderFoodItems.setQuantity(foodItemDTO.getQuantity());
            orderFoodItems.setOrderinfo(orderInfo);
            orderInfo.getOrderFoodItems().add(orderFoodItems);
            orderInfoRepo.save(orderInfo);
        }

        return ResponseEntity.ok().body("Order placed successfully");
    }


//    public ResponseEntity<String> rateOrder(Map entity) {
//        Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepo.findById((Integer) entity.get("restaurantid"));
//        Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber((String) entity.get("phonenumber"));
//        Optional<OrderInfo> order = orderInfoRepo.findByUseridAndOrderid(userInfo.get().getUserid(),
//                (Integer) entity.get("orderid"));
//        OrderInfo orderInfo = order.get();
//        orderInfo.setOrderflag(1);
//        orderInfoRepo.save(orderInfo);
//        RestaurantInfo rest = restaurantInfo.get();
//        int id = (Integer) (entity.get("restaurantrating"));
//        Float f = new Float(id);
//        float f1 = f.floatValue();
//        Float rating = 0f;
//        if (rest.getRestaurantrating() == 0.0) {
//            // rating = (Float)(entity.get("restaurantrating"));
//            rating = f1;
//            rest.setRestaurantrating(rating);
//            rest.setNumofrating(rest.getNumofrating() + 1);
//            restaurantInfoRepo.save(rest);
//
//        } else {
//
//            // rating = (float) (((rest.getRestaurantrating() * rest.getNumofrating())
//            // + (Double) entity.get("restaurantrating")) / (rest.getNumofrating() + 1));
//            ///////////////////
//            rating = (float) (((rest.getRestaurantrating() * rest.getNumofrating())
//                    + f1) / (rest.getNumofrating() + 1));
//            rest.setRestaurantrating(rating);
//            rest.setNumofrating(rest.getNumofrating() + 1);
//            restaurantInfoRepo.save(rest);
//        }
//        RestaurantRating restaurantRating = new RestaurantRating();
//
//        restaurantRating.setName(userInfo.get().getName());
//        restaurantRating.setRestaurantid((Integer) entity.get("restaurantid"));
//        restaurantRating.setRestaurantname(rest.getRestaurantname());
//        restaurantRating.setRestaurantrating(rest.getRestaurantrating());
//        restaurantRating.setRestaurantreview((String) entity.get("restaurantreview"));
//        restaurantRatingRepo.save(restaurantRating);
//
//        ArrayList<String> fooditemid = (ArrayList) entity.get("fooditemid");
//
//        if (fooditemid.isEmpty()) {
//            return ResponseEntity.ok().body("success");
//        } else {
//            ListIterator<String> ll = fooditemid.listIterator();
//
//            ArrayList<String> fooditemrating = (ArrayList) entity.get("fooditemrating");
//            ListIterator<String> ratingitr = fooditemrating.listIterator();
//
//            ArrayList<String> fooditemreview = (ArrayList) entity.get("fooditemreview");
//            ListIterator<String> review = fooditemreview.listIterator();
//
//            while (ll.hasNext()) {
//
//                FoodItemRating foodrating = new FoodItemRating();
//                foodrating.setName(userInfo.get().getName());
//                foodrating.setRestaurantid((Integer) entity.get("restaurantid"));
//                foodrating.setRestaurantname(rest.getRestaurantname());
//
//                String s = ll.next();
//                Optional<FoodItem> food = foodItemRepo.findById(Integer.parseInt(s));
//                FoodItem foodItem = food.get();
//                foodrating.setFooditemid(Integer.parseInt(s));
//                foodrating.setFoodname(foodItem.getFoodname());
//
//                String rate = ratingitr.next();
//                System.out.println("########################" + rate);
//                System.out.println("***************************" + Double.parseDouble(rate));
//
//                foodrating.setFooditemrating(Double.parseDouble(rate));
//                String fReview = review.next();
//                foodrating.setFooditemreview(fReview);
//                foodItemRatingRepo.save(foodrating);
//                Double foodRating = 0.0;
//
//                if (foodItem.getFooditemrating() == 0.0) {
//
//                    foodItem.setFooditemrating(Double.parseDouble(rate));
//                    foodItem.setNumofrating(foodItem.getNumofrating() + 1);
//                    foodItemRepo.save(foodItem);
//
//                } else {
//
//                    foodRating = ((foodItem.getFooditemrating() * foodItem.getNumofrating())
//                            + Double.parseDouble(rate)) / (foodItem.getNumofrating() + 1);
//
//                    foodItem.setFooditemrating(foodRating);
//                    foodItem.setNumofrating(foodItem.getNumofrating() + 1);
//                    foodItemRepo.save(foodItem);
//                }
//
//            }
//
//        }
//
//        return ResponseEntity.ok().body("success");
//    }
    public ResponseEntity<String> rateOrder(RateOrderDTO rateOrderDTO) {
        Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepo.findById(rateOrderDTO.getRestaurantid());
        Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber(rateOrderDTO.getPhonenumber());
        Optional<OrderInfo> order = orderInfoRepo.findByUseridAndOrderid(userInfo.get().getUserid(), rateOrderDTO.getOrderid());

        if (!order.isPresent() || !restaurantInfo.isPresent() || !userInfo.isPresent()) {
            return new ResponseEntity<>("Invalid order or restaurant", HttpStatus.BAD_REQUEST);
        }

        OrderInfo orderInfo = order.get();
        orderInfo.setOrderflag(1);
        orderInfoRepo.save(orderInfo);

        RestaurantInfo rest = restaurantInfo.get();
        float updatedRestaurantRating = updateRestaurantRating(rest, rateOrderDTO.getRestaurantrating());

        rest.setRestaurantrating(updatedRestaurantRating);
        rest.setNumofrating(rest.getNumofrating() + 1);
        restaurantInfoRepo.save(rest);

        RestaurantRating restaurantRating = new RestaurantRating();
        restaurantRating.setName(userInfo.get().getName());
        restaurantRating.setRestaurantid(rateOrderDTO.getRestaurantid());
        restaurantRating.setRestaurantname(rest.getRestaurantname());
        restaurantRating.setRestaurantrating(updatedRestaurantRating);
        restaurantRating.setRestaurantreview(rateOrderDTO.getRestaurantreview());
        restaurantRatingRepo.save(restaurantRating);

        // Iterate over the food item ratings
        for (RateOrderDTO.FoodItemRatingDTO foodItemRatingDTO : rateOrderDTO.getFoodItemRatings()) {
            Optional<FoodItem> foodItemOpt = foodItemRepo.findById(foodItemRatingDTO.getFooditemid());
            if (foodItemOpt.isPresent()) {
                FoodItem foodItem = foodItemOpt.get();
                Double updatedFoodItemRating = updateFoodItemRating(foodItem, foodItemRatingDTO.getFooditemrating());

                foodItem.setFooditemrating(updatedFoodItemRating);
                foodItem.setNumofrating(foodItem.getNumofrating() + 1);
                foodItemRepo.save(foodItem);

                FoodItemRating foodItemRating = new FoodItemRating();
                foodItemRating.setName(userInfo.get().getName());
                foodItemRating.setRestaurantid(rateOrderDTO.getRestaurantid());
                foodItemRating.setFooditemid(foodItemRatingDTO.getFooditemid());
                foodItemRating.setFoodname(foodItem.getFoodname());
                foodItemRating.setFooditemrating(foodItemRatingDTO.getFooditemrating());
                foodItemRating.setFooditemreview(foodItemRatingDTO.getFooditemreview());
                foodItemRatingRepo.save(foodItemRating);
            }
        }

        return ResponseEntity.ok().body("Rating submitted successfully");
    }

    private float updateRestaurantRating(RestaurantInfo rest, int newRating) {
        return (rest.getRestaurantrating() * rest.getNumofrating() + newRating) / (rest.getNumofrating() + 1);
    }

    private double updateFoodItemRating(FoodItem foodItem, double newRating) {
        return (foodItem.getFooditemrating() * foodItem.getNumofrating() + newRating) / (foodItem.getNumofrating() + 1);
    }

    

    public ResponseEntity<List<FooditemDetails>> getAllFoodItems() {
        List<FoodItem> foodItem = foodItemRepo.findAll();
        ListIterator<FoodItem> itr = foodItem.listIterator();

        List<FooditemDetails> fid = new ArrayList<FooditemDetails>();

        while (itr.hasNext()) {
            FoodItem fooditem = itr.next();

            RestaurantInfo ri = fooditem.getRestaurantInfo();
            FooditemDetails fs = new FooditemDetails(ri.getRestaurantid(), fooditem, ri.getRestaurantname());
            fid.add(fs);

        }
        return ResponseEntity.ok().body(fid);
    }

    public ResponseEntity<List<OrderInfo>> getAllOrderDetails(Map entity) {
        Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber((String) entity.get("phonenumber"));
        UserInfo user = userInfo.get();
        int id = user.getUserid();
        List<OrderInfo> oi = orderInfoRepo.findAllByUserid(id);
        if (oi.isEmpty()) {
            return ResponseEntity.ok().body(oi);
        }
        return ResponseEntity.ok().body(oi);
    }

}
