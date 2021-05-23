package com.danexpc.web_library.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LiteratureCategory implements Identifiable<Integer> {

    private Integer id;

    private String name;

    public LiteratureCategory(String name) {
        this.name = name;
    }

    public LiteratureCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
