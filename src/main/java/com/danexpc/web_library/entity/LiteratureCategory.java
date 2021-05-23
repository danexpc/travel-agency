package com.danexpc.web_library.entity;

import java.util.Objects;

public class LiteratureCategory implements Identifiable<Integer> {

    private Integer id;

    private String categoryName;

    public LiteratureCategory() {
    }

    public LiteratureCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public LiteratureCategory(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (LiteratureCategory) o;
        return id.equals(that.id) && Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName);
    }

    @Override
    public String toString() {
        return "LiteratureCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
