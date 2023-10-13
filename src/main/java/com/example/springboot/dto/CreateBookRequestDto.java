package com.example.springboot.dto;

import com.example.springboot.model.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotNull
    @Size(min = 2)
    private String title;

    @NotNull
    @Size(min = 2)
    private String author;

    @NotNull
    @Size(min = 2)
    private String isbn;

    @NotNull
    @Min(value = 0)
    private BigDecimal price;

    private String description;
    private String coverImage;
    private Set<Category> categories;
}
