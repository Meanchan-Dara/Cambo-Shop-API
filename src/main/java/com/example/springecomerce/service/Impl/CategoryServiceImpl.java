package com.example.springecomerce.service.Impl;

import com.example.springecomerce.Repo.CategoryRepository;
import com.example.springecomerce.dto.Request.CategoryRequestDto;
import com.example.springecomerce.dto.Response.CategoryResponseDto;
import com.example.springecomerce.entity.Category;
import com.example.springecomerce.exception.ResourceNotFoundException;
import com.example.springecomerce.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto requestDto) {
        Category category = new Category();

        category.setName(requestDto.getName());
        category.setDescription(requestDto.getDescription());

        Category saved = categoryRepository.save(category);

        return mapToResponseDto(saved);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {

        return categoryRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());

    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));


        return mapToResponseDto(category);
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto requestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with id " + id)
        );

        category.setName(requestDto.getName());
        category.setDescription(requestDto.getDescription());

        Category updated = categoryRepository.save(category);

        return mapToResponseDto(updated);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with id " + id)
        );

        categoryRepository.delete(category);

    }

    private CategoryResponseDto mapToResponseDto(Category category) {
        CategoryResponseDto responseDto = new CategoryResponseDto();

        responseDto.setId(category.getId());
        responseDto.setName(category.getName());
        responseDto.setDescription(category.getDescription());

        return responseDto;
    }
}
