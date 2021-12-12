package com.danexpc.agency.dto.request;

import com.danexpc.agency.entity.ScheduleModel;
import com.danexpc.agency.helpers.DateTimeConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequestDto {

    Integer tourId;

    Integer startLocationId;

    Integer hotelId;

    BigDecimal price;

    Long departureDate;

    Long duration;

    Boolean isOnFire;

    Integer totalPlacesQty;

    Integer remainingPlacesQty;

    public ScheduleModel buildModel() {
        ScheduleModel model = new ScheduleModel();
        model.setTourId(tourId);
        model.setStartLocationId(startLocationId);
        model.setHotelId(hotelId);
        model.setPrice(price);
        model.setDepartureDate(DateTimeConverter.fromEpoch(departureDate));
        model.setDuration(duration);
        model.setIsOnFire(isOnFire);
        model.setTotalPlacesQty(totalPlacesQty);
        model.setRemainingPlacesQty(remainingPlacesQty);

        return model;
    }
}
