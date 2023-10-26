package com.example.springboot.service;

import com.example.springboot.dto.OrderDto;
import com.example.springboot.dto.OrderItemDto;
import com.example.springboot.mapper.OrderMapper;
import com.example.springboot.model.CartItem;
import com.example.springboot.model.Order;
import com.example.springboot.model.OrderItem;
import com.example.springboot.model.User;
import com.example.springboot.repository.book.order.OrderRepository;
import com.example.springboot.repository.cartitem.CartItemRepository;
import com.example.springboot.repository.orderitem.OrderItemRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto completeOrder(Long id, String address) {
        Order order = new Order();
        User user = new User();
        user.setId(id);
        order.setUser(user);
        order.setShippingAddress(address);
        order.setStatus(Order.Status.PENDING);
        Set<CartItem> allByShoppingCartId = cartItemRepository.findAllByShoppingCartId(id);
        Set<OrderItem> orderItems;
        orderItems = allByShoppingCartId.stream()
                        .map(item -> {
                            OrderItem orderItem = new OrderItem();
                            orderItem.setOrder(order);
                            orderItem.setBook(item.getBook());
                            orderItem.setPrice(item.getBook().getPrice());
                            orderItem.setQuantity(item.getQuantity());
                            return orderItem;
                        })
                        .collect(Collectors.toSet());
        order.setOrderItems(orderItems);
        BigDecimal setTotal = allByShoppingCartId.stream()
                .map(i -> i.getBook().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal(setTotal);
        order.setOrderDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);
        //Clear ShoppingCart
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public List<OrderDto> getAllOrder(Long id) {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public void update(Long id) {

    }

    @Override
    public OrderDto getById(Long id) {
        return null;
    }

    @Override
    public OrderItemDto getOrderItem(Long orderId, Long id) {
        return null;
    }
}
