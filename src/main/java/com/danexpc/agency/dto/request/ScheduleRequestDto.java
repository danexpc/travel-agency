package com.danexpc.agency.dto.request;

import com.danexpc.agency.entity.ScheduleModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequestDto {

    Integer tourId;

    Integer startLocationId;

    Integer hotelId;

    BigDecimal price;

    LocalDateTime departureDate;

    Long duration;

    Boolean isOnFire;

    Integer totalPlaceQty;

    Integer remainingPlacesQty;

    public ScheduleModel buildModel() {
        ScheduleModel model = new ScheduleModel();
        model.setTourId(tourId);
        model.setStartLocationId(startLocationId);
        model.setHotelId(hotelId);
        model.setPrice(price);
        model.setDepartureDate(departureDate);
        model.setDuration(duration);
        model.setIsOnFire(isOnFire);
        model.setTotalPlaceQty(totalPlaceQty);
        model.setRemainingPlacesQty(remainingPlacesQty);

        return model;
    }
}
