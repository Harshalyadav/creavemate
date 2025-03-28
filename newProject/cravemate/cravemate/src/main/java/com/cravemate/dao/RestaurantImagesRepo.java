package com.cravemate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cravemate.pojos.RestaurantImages;

import jakarta.transaction.Transactional;

public interface RestaurantImagesRepo extends JpaRepository<RestaurantImages, Integer> {
    @Query(value = "DELETE FROM restaurant_images where restaurant_id = :rId", nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteByRestaurantid(Integer rId);
}
