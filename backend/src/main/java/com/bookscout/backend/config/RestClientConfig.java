package com.bookscout.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Configuration
public class RestClientConfig {
    @Value("${google.books.base.url}")
    private String baseUrl;

    @Bean
    public RestClient googleBooksRestClient() {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
