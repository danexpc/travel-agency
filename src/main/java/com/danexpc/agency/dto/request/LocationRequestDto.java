package com.danexpc.agency.dto.request;

import com.danexpc.agency.model.LocationModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequestDto {

    String note;

    String address;

    String street;

    String city;

    String country;

    public LocationModel buildModel() {
        LocationModel model = new LocationModel();
        model.setNote(note);
        model.setAddress(address);
        model.setStreet(street);
        model.setCity(city);
        model.setCountry(country);

        return model;
    }
}
