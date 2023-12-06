package org.example.entity;

import java.math.BigDecimal;

public class Book {
    private int bookId;
    private int authorId;
    private String title;
    private int numBooks;
    private BigDecimal price;

    // Constructors, getters, and setters
    // You can generate these using your IDE or manually define them

    // Constructor
    public Book(int bookId, int authorId, String title, int numBooks, BigDecimal price) {
        this.bookId = bookId;
        this.authorId = authorId;
        this.title = title;
        this.numBooks = numBooks;
        this.price = price;
    }

    public Book(int authorId, String title, int numBooks, BigDecimal price) {
    }

    // Getters and Setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumBooks() {
        return numBooks;
    }

    public void setNumBooks(int numBooks) {
        this.numBooks = numBooks;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

