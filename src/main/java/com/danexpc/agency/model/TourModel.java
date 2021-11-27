package com.danexpc.agency.model;

import com.danexpc.agency.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourModel implements Identifiable<Integer> {

    private Integer id;

    private Integer startLocationId;

    private Integer hotelId;

    private String name;

    private String description;

    private Integer type;

    private BigDecimal price;

    private Long departureDate;

    private Long duration;

    private Boolean isActive;

    private Boolean isOnFire;

    private Integer totalPlaceQty;

    private Integer remainingPlacesQty;
}
