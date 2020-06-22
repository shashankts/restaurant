package com.solosos.restaurant.dto;

import com.solosos.restaurant.model.Linked;
import com.solosos.restaurant.model.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantAddRequest {
    private String gstin;
    private String restaurantName;
    private String phoneNumber;
    private String emailId;
    private MultipartFile imageFile;
    private String[] linkedTo;
    private String alternatePhoneNumber;
    private String fssai;
    private String contactPersonName;
    private String contactPersonPhoneNumber;
    private String contactPersonRole;
    private String address;
    private MultipartFile restaurantImage;
}
