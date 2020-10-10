package com.dexter.labs.digitalfarm.converter;

import com.dexter.labs.digitalfarm.client.owm.model.Polygon;
import com.dexter.labs.digitalfarm.entity.Field;
import org.springframework.stereotype.Component;

@Component
public class FieldInputDtoConverter implements InputDtoConverter<Polygon, Field>{

    @Override
    public Field convert(Polygon polygon) {
        return new Field(polygon.getName(), "DEU", polygon.getId());
    }
}
