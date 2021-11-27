package com.danexpc.agency.dto;

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
public class TourRequestDto {

    private Integer startLocationId;

    private Integer hotelId;

    private String name;

    private String description;

    private Integer type;

    private BigDecimal price;

    private LocalDateTime departureDate;

    private Long duration;

    private Boolean isActive;

    private Boolean isOnFire;

    private Integer totalPlaceQty;

    private Integer remainingPlacesQty;
}
