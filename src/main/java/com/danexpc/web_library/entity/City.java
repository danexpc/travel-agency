package com.danexpc.web_library.entity;

import java.util.Objects;

public class City extends BaseEntity {

    private static final long serialVersionUID = -6697599680927919631L;

    private String postalCode;

    private String cityName;

    private String country;

    public City() {
    }

    public City(String postalCode, String cityName, String country) {
        this.postalCode = postalCode;
        this.cityName = cityName;
        this.country = country;
    }

    public City(int id, String postalCode, String cityName, String country) {
        super(id);
        this.postalCode = postalCode;
        this.cityName = cityName;
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
        if (!super.equals(o)) return false;
        var city = (City) o;
        return Objects.equals(postalCode, city.postalCode)
                && Objects.equals(cityName, city.cityName)
                && Objects.equals(country, city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), postalCode, cityName, country);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + getId() +
                ", postalCode='" + postalCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
