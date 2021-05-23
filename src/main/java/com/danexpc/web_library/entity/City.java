package com.danexpc.web_library.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class City implements Identifiable<Integer> {

    private Integer id;

    private String postalCode;

    private String name;

    private String country;

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
}
