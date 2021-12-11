package com.danexpc.agency.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserLoginResponse {
    Integer id;
    Integer type;
    Integer accessToken;
    Integer refreshToken;
}
