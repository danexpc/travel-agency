package com.danexpc.agency.dto.request;

import com.danexpc.agency.model.TourModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourRequestDto {

    String name;

    String description;

    Integer type;

    public TourModel buildModel() {
        TourModel model = new TourModel();
        model.setName(name);
        model.setDescription(description);
        model.setType(type);

        return model;
    }
}
