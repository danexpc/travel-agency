package com.danexpc.web_library.entity;

import java.util.Objects;

public class Book implements Identifiable<Integer> {

    private Integer id;

    private int isbn;

    private String bookTitle;

    private Author author;

    private LiteratureCategory category;

    private Publisher publisher;

    private int quantity;

    public Book() {
    }

    public Book(int isbn, String bookTitle, Author author, LiteratureCategory category,
                Publisher publisher, int quantity) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public Book(int id, int isbn, String bookTitle, Author author, LiteratureCategory category,
                Publisher publisher, int quantity) {
        this.id = id;
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public LiteratureCategory getCategory() {
        return category;
    }

    public void setCategory(LiteratureCategory category) {
        this.category = category;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var book = (Book) o;
        return id.equals(book.id)
                && isbn == book.isbn
                && quantity == book.quantity
                && Objects.equals(bookTitle, book.bookTitle)
                && Objects.equals(author, book.author)
                && Objects.equals(category, book.category)
                && Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, bookTitle, author, category, publisher, quantity);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn=" + isbn +
                ", bookTitle='" + bookTitle + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", publisher=" + publisher +
                ", quantity=" + quantity +
                '}';
    }
}
