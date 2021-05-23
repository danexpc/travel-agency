package com.danexpc.web_library.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Identifiable<Integer> {

    private Integer id;

    @NonNull
    private Integer isbn;

    @NonNull
    private String title;

    @NonNull
    private Author author;

    @NonNull
    private LiteratureCategory category;

    @NonNull
    private Publisher publisher;

    @NonNull
    private Integer quantity;
}
