package com.danexpc.agency.dto.response;

import com.danexpc.agency.dto.request.LocationRequestDto;
import com.danexpc.agency.interfaces.Identifiable;
import com.danexpc.agency.model.LocationModel;
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
public class LocationResponseDto extends LocationRequestDto implements Identifiable<Integer> {

    Integer id;

    public static LocationResponseDto fromModel(LocationModel model) {
        LocationResponseDto dto = new LocationResponseDto();
        dto.setId(model.getId());
        dto.setAddress(model.getAddress());
        dto.setCity(model.getCity());
        dto.setNote(model.getNote());
        dto.setCountry(model.getCountry());
        dto.setStreet(model.getStreet());

        return dto;
    }
}
