package com.example.springboot.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateCartItemRequestDto {
    @Min(value = 1)
    private Long quantity;
}
