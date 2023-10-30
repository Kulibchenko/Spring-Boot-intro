package com.example.springboot.service;

import com.example.springboot.dto.OrderDto;
import com.example.springboot.dto.OrderItemDto;
import com.example.springboot.model.Order;
import java.util.List;

public interface OrderService {
    OrderDto completeOrder(Long userId, String address);

    List<OrderDto> getAllOrder(Long userId);

    Order.Status update(Long orderId, Order.Status status);

    OrderItemDto getOrderItem(Long orderId, Long itemId, Long userId);

    List<OrderItemDto> getOrderItems(Long orderId, Long userId);
}
