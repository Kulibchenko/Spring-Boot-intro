package com.example.springboot.controller;

import com.example.springboot.dto.BookDtoWithoutCategoryIds;
import com.example.springboot.dto.CategoryDto;
import com.example.springboot.dto.CreateCategoryRequestDto;
import com.example.springboot.service.BookService;
import com.example.springboot.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category management", description = "Endpoints for managing category")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categories")
public class CategoriesController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @Operation(summary = "Get all category", description = "Get a list of all category")
    @GetMapping
    public List<CategoryDto> getAll() {
        return categoryService.findAll();
    }

    @Operation(summary = "Get a category", description = "Get a category with some id")
    @GetMapping(value = "/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @Operation(summary = "Delete category", description = "Delete a category with some id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @Operation(summary = "Create a category", description = "Create a new category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @Operation(summary = "Update a category", description = "Update a category with some id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public CategoryDto updateCategory(@PathVariable Long id,
            @RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.update(id, requestDto);
    }

    @Operation(summary = "Get Books", description = "Get all books with some category")
    @GetMapping(value = "/{id}/books")
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(
            @PathVariable Long id, Pageable pageable) {
        return bookService.findAllByCategory(id, pageable);
    }
}
