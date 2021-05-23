package com.danexpc.web_library.entity;

import java.util.Objects;

public class Publisher implements Identifiable<Integer> {

    private Integer id;

    private String name;

    private City city;

    public Publisher() {
    }

    public Publisher(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public Publisher(int id, String name, City city) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                && Objects.equals(name, publisher.name)
                && Objects.equals(city, publisher.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", publisherName='" + name + '\'' +
                ", city=" + city +
                '}';
    }
}
