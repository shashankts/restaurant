package com.solosos.restaurant.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long restaurantId;
    private String gstin;
    private String restaurantName;
    private String phoneNumber;
    private String emailId;
    @OneToMany(targetEntity = MenuItem.class,cascade = CascadeType.ALL)
    @JoinColumn (name = "resmen_fk",referencedColumnName = "restaurantId")
    private List<MenuItem> menuItemList;
    @OneToMany(targetEntity=Linked.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "reslin_fk",referencedColumnName = "restaurantId")
    private List<Linked> linkedWith;

    @OneToOne(targetEntity = RestaurantPic.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurantId")
    private RestaurantPic restaurantPic;
    private String alternatePhoneNumber;
    private String fssai;
    private String contactPersonName;
    private String contactPersonPhoneNumber;
    private String contactPersonRole;
    private String address;

}
