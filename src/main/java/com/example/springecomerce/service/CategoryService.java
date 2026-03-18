package com.example.springecomerce.service;

import com.example.springecomerce.dto.Request.CategoryRequestDto;
import com.example.springecomerce.dto.Response.CategoryResponseDto;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto requestDto);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryById(Long id);

    CategoryResponseDto updateCategory(Long id, CategoryRequestDto requestDto);

    void deleteCategory(Long id);
}
