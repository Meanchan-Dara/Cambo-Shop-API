package com.example.springecomerce.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CategoryRequestDto {

    @NotBlank(message = "Category name is required")
    @Size(max = 100 , message = "Category name must be less than 100 characters")
    private String name;

    @Size(max = 500 , message = "Category description must be less than 500 characters")
    private String description;

}
