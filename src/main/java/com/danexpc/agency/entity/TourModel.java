package com.danexpc.agency.entity;

import com.danexpc.agency.entity.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourModel implements Identifiable<Integer> {

    private Integer id;

    private String name;

    private String description;

    private Integer type;
}
