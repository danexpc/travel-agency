package com.danexpc.agency.model;

import com.danexpc.agency.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Identifiable<Integer> {

    private Integer id;

    private String note;

    private String address;

    private String street;

    private String city;

    private String country;
}
