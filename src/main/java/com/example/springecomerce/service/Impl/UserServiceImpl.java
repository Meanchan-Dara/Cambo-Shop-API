package com.example.springecomerce.service.Impl;

import com.example.springecomerce.dto.Request.UserRequestDto;
import com.example.springecomerce.dto.Response.UserResponeDto;
import com.example.springecomerce.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserResponeDto createUser(UserRequestDto requestDto) {
        return null; // សរសេរ Logic របស់អ្នកនៅទីនេះ
    }

    @Override
    public List<UserResponeDto> findAllUsers() {
        return List.of();
    }

    @Override
    public UserResponeDto findUserById(Integer id) {
        return null;
    }

    @Override
    public UserResponeDto updateUser(Long id, UserRequestDto requestDto) {
        return null;
    }

    @Override
    public UserResponeDto deleteUser(Long id) {
        return null;
    }
}