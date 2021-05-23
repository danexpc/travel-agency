package com.danexpc.web_library.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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
}
