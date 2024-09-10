package com.bookscout.backend.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GoogleBooksServiceTest {

    @Autowired
    private GoogleBooksService googleBooksService;

    private static Stream<String> provideSubjectsForSearch() {
        return Stream.of("antiques", "architecture", "art", "bibles", "biography", "body",
                "business", "comics", "computers", "cooking", "crafts", "crime", "design",
                "drama", "education", "family", "fiction", "games", "gardening", "health",
                "history", "house", "humor", "juvenile", "law", "literary", "mathematics",
                "medical", "music", "nature", "pets", "philosophy", "photography", "poetry",
                "political", "psychology", "reference", "religion", "science", "social",
                "sports", "technology", "transportation", "travel", "young");
    }

    @ParameterizedTest
    @MethodSource("provideSubjectsForSearch")
    void searchBooksBySubjectIsNotEmpty(String subject) {
        // When
        String response = googleBooksService.searchBooksBySubject(subject);

        // Then
        assertThat(response).doesNotContain("totalItems\": 0");
    }
}