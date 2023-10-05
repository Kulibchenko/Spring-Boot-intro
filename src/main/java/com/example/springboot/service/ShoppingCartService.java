package com.example.springboot.service;

import com.example.springboot.dto.CartItemDto;
import com.example.springboot.dto.CreateCartItemRequestDto;
import com.example.springboot.dto.ShoppingCartDto;
import com.example.springboot.dto.UpdateCartItemRequestDto;
import org.springframework.data.domain.Pageable;

public interface ShoppingCartService {
    CartItemDto save(CreateCartItemRequestDto requestDto, Long id);

    CartItemDto update(Long id, UpdateCartItemRequestDto requestDto);

    void delete(Long id);

    ShoppingCartDto getById(Pageable pageable, Long id);
}
