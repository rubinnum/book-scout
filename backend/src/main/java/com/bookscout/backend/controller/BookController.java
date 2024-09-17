package com.bookscout.backend.controller;

import com.bookscout.backend.dto.BooksListDTO;
import com.bookscout.backend.exception.WrongCategoryException;
import com.bookscout.backend.model.Book;
import com.bookscout.backend.service.BookService;
import com.bookscout.backend.utilities.Helper;
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
    public BooksListDTO getBooksBySubject(@PathVariable String subject) {
        if (!Helper.isValidSubject(subject)) {
            throw new WrongCategoryException("Oops, the category " + subject + " does not exist");
        }
        return new BooksListDTO(bookService.getBooksBySubject(subject));
    }
}
