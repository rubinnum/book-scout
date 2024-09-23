package com.bookscout.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
    private String publishedDate;
    private Integer pageCount;
    private String thumbnail;
    @Setter
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Book(String title, String author, String description, String publishedDate, Integer pageCount, String thumbnail) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.publishedDate = publishedDate;
        this.pageCount = pageCount;
        this.thumbnail = thumbnail;
    }
}
