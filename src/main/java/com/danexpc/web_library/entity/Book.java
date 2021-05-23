package com.danexpc.web_library.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Book implements Identifiable<Integer> {

    private Integer id;

    private int isbn;

    private String title;

    private Author author;

    private LiteratureCategory category;

    private Publisher publisher;

    private int quantity;

    public Book(int isbn, String title, Author author, LiteratureCategory category,
                Publisher publisher, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public Book(int id, int isbn, String title, Author author, LiteratureCategory category,
                Publisher publisher, int quantity) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.quantity = quantity;
    }
}
