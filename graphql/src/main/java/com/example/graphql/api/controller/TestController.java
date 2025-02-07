package com.example.graphql.api.controller;

import com.example.graphql.api.entity.Book;
import com.example.graphql.api.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    private final BookRepository bookRepository;

    public TestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/test/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}