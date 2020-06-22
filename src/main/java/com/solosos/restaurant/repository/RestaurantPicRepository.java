package com.solosos.restaurant.repository;


import com.solosos.restaurant.model.RestaurantPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RestaurantPicRepository extends JpaRepository<RestaurantPic,Long> {
}
