package com.bookscout.backend.controller;

import com.bookscout.backend.model.Book;
import com.bookscout.backend.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/{subject}")
    public List<Book> getBooksBySubject(@PathVariable String subject) throws JsonProcessingException {
        return bookService.getBooksBySubject(subject);
    }
}
