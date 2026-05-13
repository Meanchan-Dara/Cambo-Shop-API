package com.example.springecomerce.service;

import com.example.springecomerce.Repo.RoleRepository;
import com.example.springecomerce.dto.Request.LoginRequest;
import com.example.springecomerce.dto.Request.UserRequestDto;
import com.example.springecomerce.entity.Role;
import com.example.springecomerce.entity.User;
import com.example.springecomerce.Repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private com.example.springecomerce.service.JwtService jwtService;

    public void register(UserRequestDto request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Error: Role USER not found."));

        user.setRoles(List.of(userRole));

        userRepository.save(user);
    }

    @Transactional
    public String login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception e) {
            System.out.println("Login Failed: " + e.getMessage());
            throw new RuntimeException("Invalid Email or Password");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return jwtService.generateToken(user);
    }
}









