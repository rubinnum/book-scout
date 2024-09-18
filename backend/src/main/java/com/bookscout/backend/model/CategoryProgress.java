package com.bookscout.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories_progress")
@Getter
@NoArgsConstructor
public class CategoryProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Integer booksDisplayed;
    private Integer booksFetched;

    public CategoryProgress(Category category, Integer booksDisplayed, Integer booksFetched) {
        this.category = category;
        this.booksDisplayed = booksDisplayed;
        this.booksFetched = booksFetched;
    }
}
