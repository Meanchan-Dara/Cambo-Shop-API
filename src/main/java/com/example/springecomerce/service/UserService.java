package com.example.springecomerce.service;

import com.example.springecomerce.dto.Request.UserRequestDto;
import com.example.springecomerce.dto.Response.UserResponeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponeDto createUser(UserRequestDto requestDto);

    List<UserResponeDto> findAllUsers();

    UserResponeDto findUserById(Integer id);

    UserResponeDto updateUser(Long id,UserRequestDto requestDto);

    UserResponeDto deleteUser(Long id);

}
