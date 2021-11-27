package com.danexpc.agency.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequestDto {

    private Integer locationId;

    private String name;

    private String description;

    private Integer type;
}
