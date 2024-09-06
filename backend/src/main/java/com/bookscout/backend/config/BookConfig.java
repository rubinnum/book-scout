package com.bookscout.backend.config;

import com.bookscout.backend.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {
    @Bean
    public CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {

        };
    }
}
