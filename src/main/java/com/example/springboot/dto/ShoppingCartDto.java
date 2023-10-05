package com.example.springboot.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ShoppingCartDto {
    private Long id;
    private Set<CartItemDto> cartItemsDto;
}
