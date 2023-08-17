package com.example.springboot.repository;

import com.example.springboot.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
