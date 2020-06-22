package com.solosos.restaurant.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
