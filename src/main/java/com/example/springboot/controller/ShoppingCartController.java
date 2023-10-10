package com.example.springboot.controller;

import com.example.springboot.dto.CartItemDto;
import com.example.springboot.dto.CreateCartItemRequestDto;
import com.example.springboot.dto.ShoppingCartDto;
import com.example.springboot.dto.UpdateCartItemRequestDto;
import com.example.springboot.model.User;
import com.example.springboot.service.ShoppingCartService;
import com.example.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ShoppingCart management", description = "Endpoints for managing shoppingCart")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @Operation(summary = "Add items", description = "Add books to shoppingCart")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public CartItemDto addToCart(@RequestBody @Valid CreateCartItemRequestDto requestDto,
                                      Authentication authentication) {
        Long id = ((User) authentication
                .getPrincipal()).getId();
        return shoppingCartService.addToCart(requestDto, id);
    }

    @Operation(summary = "Get all items", description = "Get a list of all items from shoppingCart")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ShoppingCartDto getAll(Authentication authentication, Pageable pageable) {
        Long id = ((User) authentication
                .getPrincipal()).getId();
        return shoppingCartService.getById(pageable, id);
    }

    @Operation(summary = "Delete items", description = "Delete items from shoppingCart")
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping(value = "/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        shoppingCartService.delete(id);
    }

    @Operation(summary = "Update items", description = "Update quantity of items in shoppingCart")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/cart-items/{id}")
    public CartItemDto updateCartItem(@PathVariable Long id,
                                      @RequestBody @Valid UpdateCartItemRequestDto requestDto) {
        return shoppingCartService.update(id, requestDto);
    }
}
