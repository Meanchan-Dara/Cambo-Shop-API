package com.example.springecomerce.controller;

import com.example.springecomerce.dto.Request.UserRequestDto;
import com.example.springecomerce.dto.Response.AuthResponse;
import com.example.springecomerce.dto.Request.LoginRequest;
import com.example.springecomerce.entity.User;
import com.example.springecomerce.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name="Auth",description = "API Auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDto request) {
        authService.register(request);
        return ResponseEntity.ok(Map.of("message", "User registered successfully!"));
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return new AuthResponse(authService.login(request));
    }
}


