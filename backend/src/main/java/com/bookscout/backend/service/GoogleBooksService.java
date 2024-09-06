package com.bookscout.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GoogleBooksService {
    @Value("${google.books.api.key}")
    private String apiKey;

    private final RestClient googleBooksRestClient;

    public GoogleBooksService(RestClient googleBooksRestClient) {
        this.googleBooksRestClient = googleBooksRestClient;
    }

    public String searchBooksBySubject(String subject) {
        return googleBooksRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/volumes")
                        .queryParam("q", String.format("subject:%s", subject))
                        .queryParam("key", apiKey)
                        .queryParam("maxResults", 40)
                        .queryParam("langRestrict", "en")
                        .build()
                )
                .retrieve()
                .body(String.class);
    }

}
