package com.example.springboot.repository.book;

import com.example.springboot.model.Book;
import com.example.springboot.model.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findAllByCategories(Category category, Pageable pageable);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.categories")
    Page<Book> findAll(Pageable pageable);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.categories WHERE b.id = :id")
    Optional<Book> findById(@Param("id")Long id);
}
