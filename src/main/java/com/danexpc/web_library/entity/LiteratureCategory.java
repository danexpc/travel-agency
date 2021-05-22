package com.danexpc.web_library.entity;

import java.util.Objects;

public class LiteratureCategory extends BaseEntity {

    private String categoryName;

    public LiteratureCategory() {
    }

    public LiteratureCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public LiteratureCategory(int id, String categoryName) {
        super(id);
        this.categoryName = categoryName;
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
        if (!super.equals(o)) return false;
        var that = (LiteratureCategory) o;
        return Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), categoryName);
    }

    @Override
    public String toString() {
        return "LiteratureCategory{" +
                "id=" + getId() +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
