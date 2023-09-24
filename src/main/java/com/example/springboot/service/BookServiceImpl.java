package com.example.springboot.service;

import com.example.springboot.dto.BookDto;
import com.example.springboot.dto.BookDtoWithoutCategoryIds;
import com.example.springboot.dto.BookSearchParameters;
import com.example.springboot.dto.CreateBookRequestDto;
import com.example.springboot.exception.EntityNotFoundException;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.model.Book;
import com.example.springboot.model.Category;
import com.example.springboot.repository.book.BookRepository;
import com.example.springboot.repository.book.BookSpecificationBuilder;
import com.example.springboot.repository.category.CategoryRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;
    private final CategoryRepository categoryRepository;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        book.setId(id);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cant find book with id " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        List<BookDto> books = bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
        return books;
    }

    @Override
    public List<BookDto> search(BookSearchParameters params) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(params);
        return bookRepository.findAll(bookSpecification)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findAllByCategory(Long id, Pageable pageable) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(value -> bookRepository.findAllByCategories(value, pageable).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList()).orElse(null);
    }
}
