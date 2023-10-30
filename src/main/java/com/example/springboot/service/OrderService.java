package com.example.springboot.service;

import com.example.springboot.dto.OrderDto;
import com.example.springboot.dto.OrderItemDto;
import java.util.List;

public interface OrderService {
    OrderDto completeOrder(Long userId, String address);

    List<OrderDto> getAllOrder(Long userId);

    void update(Long order);

    OrderDto getById(Long userId);

    OrderItemDto getOrderItem(Long orderId, Long itemId, Long userId);

    List<OrderItemDto> getOrderItems(Long orderId, Long userId);
}
