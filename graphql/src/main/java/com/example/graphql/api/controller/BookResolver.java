package com.example.graphql.api.controller;

import com.example.graphql.api.entity.Book;
import com.example.graphql.api.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookResolver {
    private final BookRepository bookRepository;

    public BookResolver(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @QueryMapping
    public List<Book> books() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Book book(@Argument Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Book addBook(@Argument String title, @Argument String author, @Argument int price, @Argument String publisher) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);
        book.setPublisher(publisher);
        return bookRepository.save(book);
    }

    @MutationMapping
    public Book updateBook(@Argument Long id, @Argument String title, @Argument String author, @Argument int price, @Argument String publisher) {
        return bookRepository.findById(id).map(book -> {
            if (title != null) book.setTitle(title);
            if (author != null) book.setAuthor(author);
            if (price != 0) book.setPrice(price);
            if (publisher != null) book.setPublisher(publisher);
            return bookRepository.save(book);
        }).orElse(null);
    }
}
