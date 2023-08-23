package com.example.springboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private String price;
    private String description;
    private String coverImage;
}
