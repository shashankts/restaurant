package com.solosos.restaurant.exception;

public class RestaurantNotFoundException extends Throwable {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
