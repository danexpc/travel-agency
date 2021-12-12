package com.danexpc.agency.dto.response;

import com.danexpc.agency.dto.request.ScheduleRequestDto;
import com.danexpc.agency.entity.interfaces.Identifiable;
import com.danexpc.agency.entity.ScheduleModel;
import com.danexpc.agency.helpers.DateTimeConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.ZoneId;

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
        dto.setDepartureDate(DateTimeConverter.dateTimeToEpoch(model.getDepartureDate()));
        dto.setDuration(model.getDuration());
        dto.setIsOnFire(model.getIsOnFire());
        dto.setTotalPlacesQty(model.getTotalPlacesQty());
        dto.setRemainingPlacesQty(model.getRemainingPlacesQty());

        return dto;
    }
}
