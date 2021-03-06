package com.danexpc.agency.entity;

import com.danexpc.agency.entity.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements Identifiable<Integer> {

    private Integer id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String city;

    private Boolean isBlocked;

    private Integer type;
}
