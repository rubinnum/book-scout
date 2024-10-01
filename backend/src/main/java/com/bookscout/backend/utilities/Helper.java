package com.bookscout.backend.utilities;

import com.bookscout.backend.model.Category;
import com.bookscout.backend.service.CategoryProgressService;
import com.bookscout.backend.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Helper {
    private final CategoryService categoryService;
    private final CategoryProgressService categoryProgressService;

    public Helper(CategoryService categoryService, CategoryProgressService categoryProgressService) {
        this.categoryService = categoryService;
        this.categoryProgressService = categoryProgressService;
    }

    public boolean isValidSubject(String subject) {
        List<String> subjectsList = categoryService.getAllCategories().stream().map(Category::getName).toList();
        return (subjectsList.contains(subject));
    }

    public boolean categoryProgressExist(Category category) {
        return categoryProgressService.existsByCategory(category);
    }
}
