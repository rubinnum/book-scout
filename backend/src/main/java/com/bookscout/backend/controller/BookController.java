package com.bookscout.backend.controller;

import com.bookscout.backend.dto.BooksListDTO;
import com.bookscout.backend.exception.WrongCategoryException;
import com.bookscout.backend.service.BookService;
import com.bookscout.backend.utilities.Helper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/v1")
public class BookController {
    private final BookService bookService;
    private final Helper helper;

    public BookController(BookService bookService, Helper helper) {
        this.bookService = bookService;
        this.helper = helper;
    }

    @GetMapping("/books/{subject}")
    public BooksListDTO getBooksBySubject(@PathVariable String subject) {
        if (!helper.isValidSubject(subject)) {
            throw new WrongCategoryException("Oops, the category " + subject + " does not exist");
        }
        return new BooksListDTO(bookService.getBooksBySubject(subject));
    }
}
