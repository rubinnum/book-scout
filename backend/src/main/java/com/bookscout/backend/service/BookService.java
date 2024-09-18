package com.bookscout.backend.service;

import com.bookscout.backend.dto.BookDTO;
import com.bookscout.backend.mapper.BookMapper;
import com.bookscout.backend.model.Book;
import com.bookscout.backend.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {
    private final GoogleBooksService googleBooksService;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(GoogleBooksService googleBooksService, BookRepository bookRepository, BookMapper bookMapper) {
        this.googleBooksService = googleBooksService;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<Book> getBooksBySubject(String subject) {
        bookRepository.deleteAll();
        String response = googleBooksService.searchBooksBySubject(subject);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("items");

            for (JsonNode item : items) {
                BookDTO bookDTO = objectMapper.treeToValue(item, BookDTO.class);
                if (bookIsValid(bookDTO)) {
                    Book book = bookMapper.apply(bookDTO);
                    bookRepository.save(book);
                }
            }
        } catch (JsonProcessingException e) {
            log.error("JSON was not processed");
        }

        return bookRepository.findAll();
    }

    private boolean bookIsValid(BookDTO bookDTO) {
        String bookLanguage = bookDTO.getVolumeInfo().getLanguage();
        String description = bookDTO.getVolumeInfo().getDescription();
        String thumbnail = bookDTO.getVolumeInfo().getImageLinks().getThumbnail();

        return bookLanguage.equals("en") && description != null && thumbnail != null;
    }
}
