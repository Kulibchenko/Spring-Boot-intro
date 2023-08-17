package com.example.springboot;

import com.example.springboot.model.Book;
import com.example.springboot.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootIntroApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootIntroApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setAuthor("Taras");
            book.setTitle("Kobzar");
            book.setIsbn("2742");
            book.setPrice(BigDecimal.valueOf(100));
            bookService.save(book);
            System.out.println(bookService.findAll());
        };
    }
}
