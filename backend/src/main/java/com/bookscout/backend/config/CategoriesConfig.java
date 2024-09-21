package com.bookscout.backend.config;

import com.bookscout.backend.model.Category;
import com.bookscout.backend.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CategoriesConfig {
    @Bean
    public CommandLineRunner initializeCategories(CategoryRepository categoryRepository) {
        return args -> {
            List<String> categoriesNames = List.of(
                    "antiques", "architecture", "art", "bibles", "biography", "body",
                    "business", "comics", "computers", "cooking", "crafts", "crime", "design",
                    "drama", "education", "family", "fiction", "games", "gardening", "health",
                    "history", "house", "humor", "juvenile", "law", "literary", "mathematics",
                    "medical", "music", "nature", "pets", "philosophy", "photography", "poetry",
                    "political", "psychology", "reference", "religion", "science", "social",
                    "sports", "technology", "transportation", "travel", "young"
            );
            for (String name : categoriesNames) {
                Category category = new Category(name);
                categoryRepository.save(category);
            }
        };
    }
}
