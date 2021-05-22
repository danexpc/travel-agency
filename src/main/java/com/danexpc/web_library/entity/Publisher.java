package com.danexpc.web_library.entity;

import java.util.Objects;

public class Publisher extends BaseEntity {

    private String publisherName;

    private City city;

    public Publisher() {
    }

    public Publisher(String publisherName, City city) {
        this.publisherName = publisherName;
        this.city = city;
    }

    public Publisher(int id, String publisherName, City city) {
        super(id);
        this.publisherName = publisherName;
        this.city = city;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        var publisher = (Publisher) o;
        return Objects.equals(publisherName, publisher.publisherName)
                && Objects.equals(city, publisher.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publisherName, city);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + getId() +
                ", publisherName='" + publisherName + '\'' +
                ", city=" + city +
                '}';
    }
}
