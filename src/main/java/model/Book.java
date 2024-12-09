package model;

import java.time.LocalDate;

public class Book {
    private Long id;
    private String title;
    private String author;
    private LocalDate publishDate;
    private Integer Stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getStock() {
        return Stock;
    }

    public int setStock(int stock) {
        Stock = stock;
        return stock;
    }

    @Override
    public String toString() {
        return "Book: Id: " + id
                + " Title: " + title
                + " Author: " + author +
                " Publish Date: " + publishDate +
                " Stock " + Stock;
    }
}
