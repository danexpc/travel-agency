package com.danexpc.agency.entity;

import com.danexpc.agency.entity.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleModel implements Identifiable<Integer> {

    private Integer id;

    private Integer tourId;

    private Integer startLocationId;

    private Integer hotelId;

    private BigDecimal price;

    private LocalDateTime departureDate;

    private Long duration;

    private Boolean isOnFire;

    private Integer totalPlacesQty;

    private Integer remainingPlacesQty;
}
