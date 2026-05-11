package com.example.springecomerce.dto.Request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}