package com.solosos.restaurant.controller;

import com.solosos.restaurant.model.Restaurant;
import com.solosos.restaurant.repository.RestaurantRepository;
import com.solosos.restaurant.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
public class AdminController {
     private final RestaurantRepository restaurantRepository;
     private final RestaurantService  restaurantService;
     @GetMapping("admin/restaurants")
     private List<Restaurant> getRestaurants()
     {
          return restaurantService.getRestaurants();
     }

}
