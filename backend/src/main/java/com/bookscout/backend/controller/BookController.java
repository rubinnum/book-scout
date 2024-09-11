package com.bookscout.backend.controller;

import com.bookscout.backend.model.Book;
import com.bookscout.backend.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/v1")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/{subject}")
    public List<Book> getBooksBySubject(@PathVariable String subject) {
        return bookService.getBooksBySubject(subject);
    }
}
