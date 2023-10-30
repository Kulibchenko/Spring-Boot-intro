package com.example.springboot.controller;

import com.example.springboot.dto.CreateOrderRequestDto;
import com.example.springboot.dto.OrderDto;
import com.example.springboot.dto.OrderItemDto;
import com.example.springboot.model.User;
import com.example.springboot.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderDto completeOrder(Authentication authentication,
                                  @RequestBody @Valid CreateOrderRequestDto requestDto) {
        Long userId = ((User) authentication.getPrincipal()).getId();
        return orderService.completeOrder(userId, requestDto.getShippingAddress());
    }

    @GetMapping
    public List<OrderDto> getAll(Authentication authentication) {
        Long userId = ((User) authentication
                .getPrincipal()).getId();
        return orderService.getAllOrder(userId);
    }

    @GetMapping(value = "/{orderId}/items")
    public List<OrderItemDto> getOrderItems(Authentication authentication,
                                            @PathVariable Long orderId) {
        Long userId = ((User) authentication
                .getPrincipal()).getId();
        return orderService.getOrderItems(orderId, userId);
    }

    @GetMapping(value = "/{orderId}/items/{itemId}")
    public OrderItemDto getOrderItem(Authentication authentication,
                                            @PathVariable Long orderId,
                                           @PathVariable Long itemId) {
        Long userId = ((User) authentication
                .getPrincipal()).getId();
        return orderService.getOrderItem(orderId, itemId, userId);
    }

}
