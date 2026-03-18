package com.example.springecomerce.service.Impl;

import com.example.springecomerce.Repo.CategoryRepository;
import com.example.springecomerce.Repo.ProductRepository;
import com.example.springecomerce.config.SaveImageConfig;
import com.example.springecomerce.dto.Request.ProductRequestDto;
import com.example.springecomerce.dto.Response.ProductResponseDto;
import com.example.springecomerce.entity.Category;
import com.example.springecomerce.entity.Product;
import com.example.springecomerce.exception.ResourceNotFoundException;
import com.example.springecomerce.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SaveImageConfig saveImageConfig;
    private final HttpServletRequest request;

    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository, SaveImageConfig saveImageConfig, HttpServletRequest request
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.saveImageConfig = saveImageConfig;
        this.request = request;


    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Category category =  categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Category Not Found with id: " + requestDto.getCategoryId())
        );

        Product product = new Product();
        product.setName(requestDto.getName());
        product.setPrice(requestDto.getPrice());

        String savePath = saveImageConfig.saveImage(requestDto.getImageUrl());
        product.setImageUrl(savePath);
        product.setCategory(category);
        product.setDescription(requestDto.getDescription());

        Product savedProduct = productRepository.save(product);

        return mapToProduct(savedProduct);
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAll()
                .stream().map(this::mapToProduct)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product Not Found with id: " + id)
        );

        return mapToProduct(product);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product Not Found with id: " + id)
        );
        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Category Not Found with id: " + requestDto.getCategoryId())
        );

        product.setName(requestDto.getName());
        product.setPrice(requestDto.getPrice());
        product.setDescription(requestDto.getDescription());
        product.setCategory(category);

        if(requestDto.getImageUrl() != null && !requestDto.getImageUrl().isEmpty()){
            String savePath = saveImageConfig.saveImage(requestDto.getImageUrl());
            product.setImageUrl(savePath);
        }

        return mapToProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product Not Found with id: " + id)
        );
        productRepository.delete(product);
    }

    public ProductResponseDto mapToProduct(Product request) {
        ProductResponseDto ResponseDto = new ProductResponseDto();

        ResponseDto.setId(request.getId());
        ResponseDto.setName(request.getName());
        ResponseDto.setPrice(request.getPrice());
        ResponseDto.setDescription(request.getDescription());
        ResponseDto.setImageUrl(request.getImageUrl());
        ResponseDto.setCategoryId(request.getCategory().getId());
        ResponseDto.setCategoryName(request.getCategory().getName());

        return ResponseDto;
    }
}
