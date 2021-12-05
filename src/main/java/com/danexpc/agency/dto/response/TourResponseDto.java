package com.danexpc.agency.dto.response;

import com.danexpc.agency.dto.request.TourRequestDto;
import com.danexpc.agency.interfaces.Identifiable;
import com.danexpc.agency.model.TourModel;
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
public class TourResponseDto extends TourRequestDto implements Identifiable<Integer> {

    Integer id;

    public static TourResponseDto fromModel(TourModel model) {
        TourResponseDto dto = new TourResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        dto.setType(model.getType());

        return dto;
    }
}
