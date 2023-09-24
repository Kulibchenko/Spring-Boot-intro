package com.example.springboot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCategoryRequestDto {
    @NotNull
    @Size(min = 2)
    private String name;
    private String description;
}
