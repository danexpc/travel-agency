package com.danexpc.agency.dto.response;

import com.danexpc.agency.dto.request.HotelRequestDto;
import com.danexpc.agency.entity.interfaces.Identifiable;
import com.danexpc.agency.entity.HotelModel;
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
public class HotelResponseDto extends HotelRequestDto implements Identifiable<Integer> {

    Integer id;

    public static HotelResponseDto fromModel(HotelModel model) {
        HotelResponseDto dto = new HotelResponseDto();
        dto.setId(model.getId());
        dto.setLocationId(model.getLocationId());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        dto.setType(model.getType());

        return dto;
    }
}
