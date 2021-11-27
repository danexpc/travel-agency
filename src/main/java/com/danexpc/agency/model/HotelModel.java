package com.danexpc.agency.model;

import com.danexpc.agency.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelModel implements Identifiable<Integer> {

    private Integer id;

    private Integer locationId;

    private String name;

    private String description;

    private Integer type;
}
