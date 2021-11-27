package com.danexpc.agency.model;

import com.danexpc.agency.constants.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String phoneNumber;

    @NonNull
    private UserRole role;
}
