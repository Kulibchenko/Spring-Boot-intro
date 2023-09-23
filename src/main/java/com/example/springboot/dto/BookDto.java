package com.example.springboot.dto;

import com.example.springboot.model.Category;
import java.util.Set;
import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String price;
    private String description;
    private String coverImage;
    private Set<Category> categories;
}
