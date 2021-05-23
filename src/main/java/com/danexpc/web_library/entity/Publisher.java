package com.danexpc.web_library.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Publisher implements Identifiable<Integer> {

    private Integer id;

    private String name;

    private City city;

    public Publisher(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public Publisher(int id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
}
