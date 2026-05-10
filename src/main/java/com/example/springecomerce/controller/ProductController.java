package com.example.springecomerce.controller;

import com.example.springecomerce.dto.Request.ProductRequestDto;
import com.example.springecomerce.dto.Response.ProductResponseDto;
import com.example.springecomerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@Tag(name="Products",description = "API CRUD Products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> createProduct(
            @Valid @ModelAttribute ProductRequestDto requestDto,
            HttpServletRequest request
    ) {
        ProductResponseDto response = productService.createProduct(requestDto);
        return ResponseEntity.ok(toClientResponse(response, request));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(HttpServletRequest request) {
        List<ProductResponseDto> responses = productService.findAllProducts()
                .stream()
                .map(product -> toClientResponse(product, request))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        ProductResponseDto response = productService.findProductById(id);
        return ResponseEntity.ok(toClientResponse(response, request));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @Valid @ModelAttribute ProductRequestDto requestDto,
            HttpServletRequest request
    ) {
        ProductResponseDto response = productService.updateProduct(id, requestDto);
        return ResponseEntity.ok(toClientResponse(response, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    private ProductResponseDto toClientResponse(ProductResponseDto dto, HttpServletRequest request) {
        if (dto.getImageUrl() != null && !dto.getImageUrl().isBlank()) {
            dto.setImageUrl(
                    ServletUriComponentsBuilder.fromContextPath(request)
                            .path("/")
                            .path(dto.getImageUrl())
                            .toUriString()
            );
        }
        return dto;
    }
}
