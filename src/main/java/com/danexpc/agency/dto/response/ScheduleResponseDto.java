package com.danexpc.agency.dto.response;

import com.danexpc.agency.dto.request.ScheduleRequestDto;
import com.danexpc.agency.interfaces.Identifiable;
import com.danexpc.agency.model.ScheduleModel;
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
public class ScheduleResponseDto extends ScheduleRequestDto implements Identifiable<Integer> {

    Integer id;

    public static ScheduleResponseDto fromModel(ScheduleModel model) {
        ScheduleResponseDto dto = new ScheduleResponseDto();
        dto.setId(model.getId());
        dto.setTourId(model.getTourId());
        dto.setStartLocationId(model.getStartLocationId());
        dto.setHotelId(model.getHotelId());
        dto.setPrice(model.getPrice());
        dto.setPrice(model.getPrice());
        dto.setDepartureDate(model.getDepartureDate());
        dto.setDuration(model.getDuration());
        dto.setIsOnFire(model.getIsOnFire());
        dto.setTotalPlaceQty(model.getTotalPlaceQty());
        dto.setRemainingPlacesQty(model.getRemainingPlacesQty());

        return dto;
    }
}
