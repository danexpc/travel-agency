package com.danexpc.web_library.entity;

import java.util.Objects;

public class City implements Identifiable<Integer> {

    private Integer id;

    private String postalCode;

    private String name;

    private String country;

    public City() {
    }

    public City(String postalCode, String name, String country) {
        this.postalCode = postalCode;
        this.name = name;
        this.country = country;
    }

    public City(int id, String postalCode, String name, String country) {
        this.id = id;
        this.postalCode = postalCode;
        this.name = name;
        this.country = country;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var city = (City) o;
        return id.equals(city.id)
                && Objects.equals(postalCode, city.postalCode)
                && Objects.equals(name, city.name)
                && Objects.equals(country, city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postalCode, name, country);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", postalCode='" + postalCode + '\'' +
                ", cityName='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
