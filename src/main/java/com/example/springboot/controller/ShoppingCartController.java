package com.example.springboot.controller;

import com.example.springboot.dto.CartItemDto;
import com.example.springboot.dto.CreateCartItemRequestDto;
import com.example.springboot.dto.ShoppingCartDto;
import com.example.springboot.dto.UpdateCartItemRequestDto;
import com.example.springboot.model.User;
import com.example.springboot.service.ShoppingCartService;
import com.example.springboot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public CartItemDto createCartItem(@RequestBody @Valid CreateCartItemRequestDto requestDto, Authentication authentication) {
        Long id = ((User) ((UsernamePasswordAuthenticationToken) authentication).getPrincipal()).getId();
        return shoppingCartService.save(requestDto, id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ShoppingCartDto getAll(Authentication authentication, Pageable pageable) {
        Long id = ((User) ((UsernamePasswordAuthenticationToken) authentication).getPrincipal()).getId();
        return shoppingCartService.getById(pageable, id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping(value = "/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        shoppingCartService.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/cart-items/{id}")
    public CartItemDto updateCartItem(@PathVariable Long id,
            @RequestBody @Valid UpdateCartItemRequestDto requestDto) {
        return shoppingCartService.update(id, requestDto);
    }


}
