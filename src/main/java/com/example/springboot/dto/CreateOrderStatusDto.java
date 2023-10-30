package com.example.springboot.dto;

import com.example.springboot.model.Order;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderStatusDto {
    @NotNull
    private Order.Status status;
}
