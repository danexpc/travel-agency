package com.danexpc.agency.dto.request;

import com.danexpc.agency.model.UserModel;
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

    String email;

    String password;

    String firstName;

    String lastName;

    String city;

    Boolean isBlocked;

    Integer type;

    public UserModel buildModel() {
        UserModel model = new UserModel();
        model.setEmail(email);
        model.setPassword(password);
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setCity(city);
        model.setIsBlocked(isBlocked);
        model.setType(type);

        return model;
    }
}
