package com.example.springboot.controller;

import com.example.springboot.model.User;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    @PostMapping
    public OrderDto completeOrder(Authentication authentication) {
        Long id = ((User) authentication.getPrincipal()).getId();

    }


}
