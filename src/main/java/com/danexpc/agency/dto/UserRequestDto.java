package com.danexpc.agency.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String city;

    private Boolean isBlocked;

    private Integer type;
}
