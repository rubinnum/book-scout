package com.bookscout.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories_progress")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
