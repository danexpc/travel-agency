package com.danexpc.agency.dto.response;

import com.danexpc.agency.dto.request.UserRequestDto;
import com.danexpc.agency.interfaces.Identifiable;
import com.danexpc.agency.model.UserModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto extends UserRequestDto implements Identifiable<Integer> {

    Integer id;

    public static UserResponseDto fromModel(UserModel model) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(model.getId());
        dto.setEmail(model.getEmail());
        dto.setPassword(model.getPassword());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setCity(model.getCity());
        dto.setIsBlocked(model.getIsBlocked());
        dto.setType(model.getType());

        return dto;
    }
}
