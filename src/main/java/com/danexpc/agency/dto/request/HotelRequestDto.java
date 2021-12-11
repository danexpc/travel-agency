package com.danexpc.agency.dto.request;

import com.danexpc.agency.entity.HotelModel;
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

    Integer locationId;

    String name;

    String description;

    Integer type;

    public HotelModel buildModel() {
        HotelModel model = new HotelModel();
        model.setName(name);
        model.setLocationId(locationId);
        model.setDescription(description);
        model.setType(type);

        return model;
    }
}
