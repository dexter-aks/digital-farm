package com.dexter.labs.digitalfarm.converter;

import com.dexter.labs.digitalfarm.client.owm.model.Polygon;
import com.dexter.labs.digitalfarm.entity.Field;
import org.springframework.stereotype.Component;

@Component
public class FieldInputDtoConverter{

    public Field convert(Polygon polygon, String countryCode) {
        return new Field(polygon.getName(), countryCode, polygon.getId());
    }
}
