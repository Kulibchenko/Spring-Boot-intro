package com.example.springboot.dto;

import com.example.springboot.model.Book;
import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private Long bookId;
    private Long quantity;
}
