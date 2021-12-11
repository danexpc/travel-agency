package com.danexpc.agency.entity;

import com.danexpc.agency.entity.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationModel implements Identifiable<Integer> {

    private Integer id;

    private String note;

    private String address;

    private String street;

    private String city;

    private String country;
}
