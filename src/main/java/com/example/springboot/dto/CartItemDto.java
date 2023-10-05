package com.example.springboot.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long id;
    private Long bookId;
    private Long quantity;

}
