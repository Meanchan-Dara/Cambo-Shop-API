package com.example.springecomerce.dto.Request;

import com.example.springecomerce.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {


    @NotBlank(message = "Product name is required")
    @Size(max = 100 , message = "Product name must be less than 100 characters")
    private String name;

    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.0" , inclusive = false , message = "price must be greater than 0")
    private BigDecimal price;

//    @Size(max = 500 , message = "image url must be less than 500 characters")
    private MultipartFile imageUrl;

    @Size(max = 1000 , message = "description must be less than 1000 characters")
    private String description;

    @NotNull(message = "Caregory ID is required")
    private Long categoryId;

}
