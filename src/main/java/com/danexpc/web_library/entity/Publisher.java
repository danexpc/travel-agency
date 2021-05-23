package com.danexpc.web_library.entity;

import java.util.Objects;

public class Publisher implements Identifiable<Integer> {

    private Integer id;

    private String publisherName;

    private City city;

    public Publisher() {
    }

    public Publisher(String publisherName, City city) {
        this.publisherName = publisherName;
        this.city = city;
    }

    public Publisher(int id, String publisherName, City city) {
        this.id = id;
        this.publisherName = publisherName;
        this.city = city;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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
        var publisher = (Publisher) o;
        return id.equals(publisher.id)
                && Objects.equals(publisherName, publisher.publisherName)
                && Objects.equals(city, publisher.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publisherName, city);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", publisherName='" + publisherName + '\'' +
                ", city=" + city +
                '}';
    }
}
