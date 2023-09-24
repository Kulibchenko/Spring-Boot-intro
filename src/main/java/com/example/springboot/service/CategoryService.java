package com.example.springboot.service;

import com.example.springboot.dto.CategoryDto;
import com.example.springboot.dto.CreateCategoryRequestDto;
import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();

    CategoryDto save(CreateCategoryRequestDto requestDto);

    CategoryDto update(Long id, CreateCategoryRequestDto requestDto);

    void delete(Long id);

    CategoryDto getById(Long id);

}
