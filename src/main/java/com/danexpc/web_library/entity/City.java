package com.danexpc.web_library.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements Identifiable<Integer> {

    private Integer id;

    @NonNull
    private String postalCode;

    @NonNull
    private String name;

    @NonNull
    private String country;
}
