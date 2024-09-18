package com.bookscout.backend.controller;

import com.bookscout.backend.dto.CategoryProgressDTO;
import com.bookscout.backend.model.Category;
import com.bookscout.backend.repository.CategoryRepository;
import com.bookscout.backend.service.CategoryProgressService;
import com.bookscout.backend.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/v1")
public class CategoryProgressController {
    private final CategoryProgressService categoryProgressService;
    private final CategoryService categoryService;

    public CategoryProgressController(CategoryProgressService categoryProgressService, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryProgressService = categoryProgressService;
        this.categoryService = categoryService;
    }

    @PostMapping("/progress/{categoryName}")
    public CategoryProgressDTO addCategoryProgress(@PathVariable String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        return categoryProgressService.initializeCategoryProgress(category);
    }
}
