package com.danexpc.web_library.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Identifiable<Integer> {

    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String address;

    @NonNull
    private City city;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String phoneNumber;

    @NonNull
    private UserRole role;
}
