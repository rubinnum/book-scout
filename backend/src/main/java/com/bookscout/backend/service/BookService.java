package com.bookscout.backend.service;

import com.bookscout.backend.dto.BookDTO;
import com.bookscout.backend.mapper.BookApiResponseMapper;
import com.bookscout.backend.mapper.BookMapper;
import com.bookscout.backend.model.Book;
import com.bookscout.backend.model.Category;
import com.bookscout.backend.repository.BookRepository;
import com.bookscout.backend.utilities.BookApiResponse;
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
    private final CategoryService categoryService;
    private final CategoryProgressService categoryProgressService;

    public BookService(GoogleBooksService googleBooksService, BookRepository bookRepository, BookApiResponseMapper bookApiResponseMapper, BookMapper bookMapper, CategoryService categoryService, CategoryProgressService categoryProgressService) {
        this.googleBooksService = googleBooksService;
        this.bookRepository = bookRepository;
        this.bookApiResponseMapper = bookApiResponseMapper;
        this.bookMapper = bookMapper;
        this.categoryService = categoryService;
        this.categoryProgressService = categoryProgressService;
    }

    public List<BookDTO> getBooksBySubject(String subject) {
        Category category = categoryService.getCategoryByName(subject);
        int currentDisplayedBooks = 0;

        if (!categoryProgressService.existsByCategory(category)) {
            int numberOfFetchedBooks = fetchNewBatchOfBooks(subject, 0);
            categoryProgressService.initializeCategoryProgress(category, numberOfFetchedBooks);
        }
        else {
            int currentFetchedBooks = categoryProgressService.getTheNumberOfFetchedBooks(category);
            currentDisplayedBooks = categoryProgressService.getTheNumberOfDisplayedBooks(category);

            if (currentDisplayedBooks >= currentFetchedBooks * 0.8) {
                int currentFetches = categoryProgressService.getTheFetchesNumber(category);
                int googleBooksApiStartIndex = currentFetches * 40;
                int justFetchedBooks = fetchNewBatchOfBooks(subject, googleBooksApiStartIndex);

                int numberOfFetchedBooks = currentFetchedBooks + justFetchedBooks;
                categoryProgressService.updateFetchedBooksAndFetchesNumber(category, numberOfFetchedBooks, currentFetches + 1);
            }
        }

        return getListOfBooksDTO(bookRepository.findTenNotDisplayedBooksByCategory(category.getId(), currentDisplayedBooks));
    }

    private int fetchNewBatchOfBooks(String subject, Integer startIndex) {
        String response = googleBooksService.fetchBooksBySubject(subject, startIndex);

        int fetchedBooksCounter = 0;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("items");

            for (JsonNode item : items) {
                BookApiResponse bookApiResponse = objectMapper.treeToValue(item, BookApiResponse.class);
                if (bookIsValid(bookApiResponse) && bookIsUnique(bookApiResponse)) {
                    Book book = bookApiResponseMapper.apply(bookApiResponse);
                    Category category = categoryService.getCategoryByName(subject);
                    book.setCategory(category);
                    bookRepository.save(book);
                    fetchedBooksCounter += 1;
                }
            }
        } catch (JsonProcessingException e) {
            log.error("JSON was not processed");
        }

        return fetchedBooksCounter;
    }

    private boolean bookIsValid(BookApiResponse bookApiResponse) {
        String bookLanguage = bookApiResponse.getVolumeInfo().getLanguage();
        String description = bookApiResponse.getVolumeInfo().getDescription();
        return bookLanguage.equals("en") && description != null;
    }

    private boolean bookIsUnique(BookApiResponse bookApiResponse) {
        String bookTitle = bookApiResponse.getVolumeInfo().getTitle();
        return !bookRepository.existsByTitleIgnoreCase(bookTitle);
    }

    private List<BookDTO> getListOfBooksDTO(List<Book> booksList) {
        List<BookDTO> booksDTOList = new ArrayList<>();
        for (Book book : booksList) {
            booksDTOList.add(bookMapper.apply(book));
        }
        return booksDTOList;
    }
}
