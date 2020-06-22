package com.solosos.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantPic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long restaurantPicId;
    private String path;
    private double longitude;
    private double latitude;
    @OneToOne(mappedBy ="restaurantPic" )
    private Restaurant restaurant;
}
