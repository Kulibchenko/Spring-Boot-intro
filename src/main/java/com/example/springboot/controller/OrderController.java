package com.example.springboot.controller;

import com.example.springboot.dto.CreateOrderRequestDto;
import com.example.springboot.dto.CreateOrderStatusDto;
import com.example.springboot.dto.OrderDto;
import com.example.springboot.dto.OrderItemDto;
import com.example.springboot.model.Order;
import com.example.springboot.model.User;
import com.example.springboot.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Orders management", description = "Endpoints for managing orders")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Complete order", description = "Make order with items from cart")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public OrderDto completeOrder(Authentication authentication,
                                  @RequestBody @Valid CreateOrderRequestDto requestDto) {
        Long userId = ((User) authentication.getPrincipal()).getId();
        return orderService.completeOrder(userId, requestDto.getShippingAddress());
    }

    @Operation(summary = "Get orders", description = "Show all orders of current user")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public List<OrderDto> getAll(Authentication authentication) {
        Long userId = ((User) authentication
                .getPrincipal()).getId();
        return orderService.getAllOrder(userId);
    }

    @Operation(summary = "Get order items", description = "Show all items from order")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/{orderId}/items")
    public List<OrderItemDto> getOrderItems(Authentication authentication,
                                            @PathVariable Long orderId) {
        Long userId = ((User) authentication
                .getPrincipal()).getId();
        return orderService.getOrderItems(orderId, userId);
    }

    @Operation(summary = "Get item", description = "Show information about item from order")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/{orderId}/items/{itemId}")
    public OrderItemDto getOrderItem(Authentication authentication,
                                            @PathVariable Long orderId,
                                           @PathVariable Long itemId) {
        Long userId = ((User) authentication
                .getPrincipal()).getId();
        return orderService.getOrderItem(orderId, itemId, userId);
    }

    @Operation(summary = "Update order", description = "Update status of order")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public Order.Status updateOrder(@PathVariable Long id,
                                      @RequestBody @Valid CreateOrderStatusDto dto) {
        Order.Status update = orderService.update(id, dto.getStatus());
        return update;
    }
}
