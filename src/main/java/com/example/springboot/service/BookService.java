package com.example.springboot.service;

import com.example.springboot.dto.BookDto;
import com.example.springboot.dto.BookDtoWithoutCategoryIds;
import com.example.springboot.dto.BookSearchParameters;
import com.example.springboot.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    BookDto findById(Long id);

    List<BookDto> findAll(Pageable pageable);

    BookDto update(Long id, CreateBookRequestDto requestDto);

    void delete(Long id);

    List<BookDto> search(BookSearchParameters params);

    List<BookDtoWithoutCategoryIds> findAllByCategory(Long id);
}
