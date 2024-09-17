package com.bookscout.backend.controller;

import com.bookscout.backend.dto.CategoriesDTO;
import com.bookscout.backend.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/v1")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public CategoriesDTO getAllCategories() {
        return new CategoriesDTO(categoryService.getAllCategories());
    }
}
