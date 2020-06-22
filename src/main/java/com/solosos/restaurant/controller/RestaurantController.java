package com.solosos.restaurant.controller;

import com.solosos.restaurant.dto.LoginRequest;
import com.solosos.restaurant.dto.RestaurantAddRequest;
import com.solosos.restaurant.exception.RestaurantNotFoundException;
import com.solosos.restaurant.model.MenuItem;
import com.solosos.restaurant.model.Restaurant;
import com.solosos.restaurant.service.RestaurantService;
import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;




@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RestaurantController {

    private final RestaurantService restaurantService;
    @PostMapping(value = "/addRestaurant",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Restaurant singUp(@ModelAttribute RestaurantAddRequest restaurantAddRequest) throws IOException, TesseractException {
        return restaurantService.addRestaurant(restaurantAddRequest);

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return restaurantService.login(loginRequest);
    }
    @DeleteMapping("restaurant/{id}")
    public Optional<Restaurant> deleteRestaurant(@PathVariable long id) throws RestaurantNotFoundException {
        return restaurantService.deleteRestaurant(id);
    }
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants()
    {
        return restaurantService.getRestaurants();
    }





}
