package com.bookscout.backend.utilities;

import com.bookscout.backend.model.Category;
import com.bookscout.backend.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Helper {
    private final CategoryService categoryService;

    public Helper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public boolean isValidSubject(String subject) {
        List<String> subjectsList = categoryService.getAllCategories().stream().map(Category::getName).toList();
        return (subjectsList.contains(subject));
    }
}
