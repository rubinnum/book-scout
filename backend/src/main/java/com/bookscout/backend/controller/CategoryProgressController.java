package com.bookscout.backend.controller;

import com.bookscout.backend.dto.CategoryProgressDTO;
import com.bookscout.backend.model.Category;
import com.bookscout.backend.repository.CategoryRepository;
import com.bookscout.backend.service.CategoryProgressService;
import com.bookscout.backend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/v1")
public class CategoryProgressController {
    private final CategoryProgressService categoryProgressService;
    private final CategoryService categoryService;

    public CategoryProgressController(CategoryProgressService categoryProgressService, CategoryService categoryService) {
        this.categoryProgressService = categoryProgressService;
        this.categoryService = categoryService;
    }

    @PostMapping("/progress/{categoryName}")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryProgressDTO addCategoryProgress(@PathVariable String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        return categoryProgressService.initializeCategoryProgress(category);
    }

    @PutMapping("/progress/{categoryName}")
    public void updateCategoryProgress(@PathVariable String categoryName, @RequestBody Integer booksDisplayed) {
        Category category = categoryService.getCategoryByName(categoryName);
        categoryProgressService.updateCategoryProgress(category, booksDisplayed);
    }
}
