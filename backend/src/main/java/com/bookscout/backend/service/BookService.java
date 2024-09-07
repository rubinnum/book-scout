package com.bookscout.backend.service;

import com.bookscout.backend.dto.BookDTO;
import com.bookscout.backend.mapper.BookMapper;
import com.bookscout.backend.model.Book;
import com.bookscout.backend.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final GoogleBooksService googleBooksService;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(GoogleBooksService googleBooksService, BookRepository bookRepository, BookMapper bookMapper) {
        this.googleBooksService = googleBooksService;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<Book> getBooksBySubject(String subject) throws JsonProcessingException {
        bookRepository.deleteAll();
        String response = googleBooksService.searchBooksBySubject(subject);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response);
        JsonNode items = root.path("items");

        for (JsonNode item : items) {
            BookDTO bookDTO = objectMapper.treeToValue(item, BookDTO.class);
            Book book = bookMapper.apply(bookDTO);
            bookRepository.save(book);
        }
        return bookRepository.findAll();
    }
}
