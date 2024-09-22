package com.bookscout.backend.service;

import com.bookscout.backend.dto.BookDTO;
import com.bookscout.backend.mapper.BookMapper;
import com.bookscout.backend.utilities.BookApiResponse;
import com.bookscout.backend.mapper.BookApiResponseMapper;
import com.bookscout.backend.model.Book;
import com.bookscout.backend.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookService {
    private final GoogleBooksService googleBooksService;
    private final BookRepository bookRepository;
    private final BookApiResponseMapper bookApiResponseMapper;
    private final BookMapper bookMapper;

    public BookService(GoogleBooksService googleBooksService, BookRepository bookRepository, BookApiResponseMapper bookApiResponseMapper, BookMapper bookMapper) {
        this.googleBooksService = googleBooksService;
        this.bookRepository = bookRepository;
        this.bookApiResponseMapper = bookApiResponseMapper;
        this.bookMapper = bookMapper;
    }

    public List<BookDTO> getBooksBySubject(String subject) {
        if (bookRepository.count() == 0) {
            fetchNewBatchOfBooks(subject, 0);
        }

        return getListOfBooksDTO(bookRepository.findAll());
    }

    private void fetchNewBatchOfBooks(String subject, Integer startIndex) {
        String response = googleBooksService.fetchBooksBySubject(subject, startIndex);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("items");

            for (JsonNode item : items) {
                BookApiResponse bookApiResponse = objectMapper.treeToValue(item, BookApiResponse.class);
                if (bookIsValid(bookApiResponse)) {
                    Book book = bookApiResponseMapper.apply(bookApiResponse);
                    bookRepository.save(book);
                }
            }
        } catch (JsonProcessingException e) {
            log.error("JSON was not processed");
        }
    }

    private boolean bookIsValid(BookApiResponse bookApiResponse) {
        String bookLanguage = bookApiResponse.getVolumeInfo().getLanguage();
        String description = bookApiResponse.getVolumeInfo().getDescription();
        return bookLanguage.equals("en") && description != null;
    }

    private List<BookDTO> getListOfBooksDTO(List<Book> booksList) {
        List<BookDTO> booksDTOList = new ArrayList<>();
        for (Book book : booksList) {
            booksDTOList.add(bookMapper.apply(book));
        }
        return booksDTOList;
    }
}
