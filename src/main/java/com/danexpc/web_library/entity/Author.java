package com.danexpc.web_library.entity;

import java.util.Objects;

public class Author extends BaseEntity {

    private static final long serialVersionUID = 4359579741719499416L;

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
        super(id);
        this.name = name;
        this.surname = surname;
        this.pseudonym = pseudonym;
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
        if (!super.equals(o)) return false;
        var author = (Author) o;
        return Objects.equals(name, author.name) && Objects.equals(surname, author.surname)
                && Objects.equals(pseudonym, author.pseudonym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, pseudonym);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pseudonym='" + pseudonym + '\'' +
                '}';
    }
}
