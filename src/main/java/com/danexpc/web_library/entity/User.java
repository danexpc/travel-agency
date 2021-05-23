package com.danexpc.web_library.entity;

import java.util.Objects;

public class User implements Identifiable<Integer> {

    private Integer id;

    private String name;

    private String surname;

    private String address;

    private City city;

    private String email;

    private String password;

    private String phoneNumber;

    private UserRole role;

    private User() {
    }

    public User(String name, String surname, String address, City city, String email, String password,
                String phoneNumber, UserRole role) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public User(int id, String name, String surname, String address, City city, String email, String password,
                String phoneNumber, UserRole role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var user = (User) o;
        return id.equals(user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(surname, user.surname)
                && Objects.equals(address, user.address)
                && Objects.equals(city, user.city)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && Objects.equals(phoneNumber, user.phoneNumber)
                && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, address, city, email, password, phoneNumber, role);
    }


}
