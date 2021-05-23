package com.danexpc.web_library.entity;

import java.util.Objects;

public class LiteratureCategory implements Identifiable<Integer> {

    private Integer id;

    private String name;

    public LiteratureCategory() {
    }

    public LiteratureCategory(String name) {
        this.name = name;
    }

    public LiteratureCategory(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (LiteratureCategory) o;
        return id.equals(that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "LiteratureCategory{" +
                "id=" + id +
                ", categoryName='" + name + '\'' +
                '}';
    }
}
