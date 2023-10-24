package com.example.springboot.service;

import com.example.springboot.dto.OrderDto;
import com.example.springboot.dto.OrderItemDto;
import java.util.List;

public interface OrderService {
    OrderDto completeOrder(Long id, String address);

    List<OrderDto> getAllOrder(Long id);

    void update(Long id);

    OrderDto getById(Long id);

    OrderItemDto getOrderItem(Long orderId, Long id);
}
