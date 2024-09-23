package com.bookscout.backend.utilities;

import com.bookscout.backend.model.Category;
import com.bookscout.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Helper {
    private final List<String> subjectsList;

    public Helper(CategoryService categoryService) {
        this.subjectsList = categoryService.getAllCategories().stream().map(Category::getName).collect(Collectors.toList());
    }

    public boolean isValidSubject(String subject) {
        return (subjectsList.contains(subject));
    }
}
