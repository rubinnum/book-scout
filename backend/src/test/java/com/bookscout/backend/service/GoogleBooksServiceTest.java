package com.bookscout.backend.service;

import com.bookscout.backend.model.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class GoogleBooksServiceTest {

    @Autowired
    private GoogleBooksService googleBooksService;

    @Autowired
    private CategoryService categoryService;

    private List<Category> allCategories;

    @BeforeAll
    void setup() {
        allCategories = categoryService.getAllCategories();
    }

    private List<String> provideSubjectsForSearch() {
        return allCategories.stream().map(Category::getName).collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource("provideSubjectsForSearch")
    public void fetchBooksBySubjectIsNotEmpty(String subject) {
        // When
        String response = googleBooksService.fetchBooksBySubject(subject, 0);

        // Then
        assertThat(response).doesNotContain("totalItems\": 0");
    }
}