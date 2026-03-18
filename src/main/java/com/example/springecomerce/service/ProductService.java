package com.example.springecomerce.service;

import com.example.springecomerce.dto.Request.ProductRequestDto;
import com.example.springecomerce.dto.Response.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto requestDto);

    List<ProductResponseDto> findAllProducts();

    ProductResponseDto findProductById(Long id);

    ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto);

    void deleteProduct(Long id);

}
