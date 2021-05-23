package com.danexpc.web_library.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Author implements Identifiable<Integer> {

    private Integer id;

    private String name;

    private String surname;

    private String pseudonym;

    public Author(String name, String surname, String pseudonym) {
        this.name = name;
        this.surname = surname;
        this.pseudonym = pseudonym;
    }

    public Author(int id, String name, String surname, String pseudonym) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pseudonym = pseudonym;
    }
}
