package com.example.springboot.repository.book;

import com.example.springboot.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    //List<Book> findAllByCategoryId(Long categoryId);
}
