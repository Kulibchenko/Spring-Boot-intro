package com.example.springboot.service;

import com.example.springboot.dto.CategoryDto;
import com.example.springboot.dto.CreateCategoryRequestDto;
import com.example.springboot.mapper.CategoryMapper;
import com.example.springboot.model.Category;
import com.example.springboot.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto requestDto) {
        Category category = categoryMapper.toModel(requestDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto requestDto) {
        Category category = categoryMapper.toModel(requestDto);
        category.setId(id);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto getById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return categoryMapper.toDto(category.get());
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
