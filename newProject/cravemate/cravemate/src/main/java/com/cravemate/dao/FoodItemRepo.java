package com.cravemate.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cravemate.pojos.FoodItem;




public interface FoodItemRepo extends JpaRepository<FoodItem, Integer> {
    @Query(value = "SELECT* FROM food_items s WHERE s.restaurant_id = :rId AND s.food_name =:name", nativeQuery = true)
    public Optional<FoodItem> findByRestaurantidAndFoodname(Integer rId, String name);

    public List<FoodItem> findByFoodnameContaining(String name);

    public Collection<? extends FoodItem> findByFoodnameContaining(String string, Sort by);

    public List<FoodItem> findAll();

}
