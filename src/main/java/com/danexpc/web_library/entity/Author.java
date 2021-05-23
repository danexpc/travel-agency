package com.danexpc.web_library.entity;

import java.util.Objects;

public class Author implements Identifiable<Integer> {

    private Integer id;

    private String name;

    private String surname;

    private String pseudonym;

    public Author() {
    }

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

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var author = (Author) o;
        return id.equals(author.id)
                && Objects.equals(name, author.name)
                && Objects.equals(surname, author.surname)
                && Objects.equals(pseudonym, author.pseudonym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, pseudonym);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pseudonym='" + pseudonym + '\'' +
                '}';
    }
}
