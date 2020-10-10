package com.dexter.labs.digitalfarm.converter;

import com.dexter.labs.digitalfarm.client.owm.model.Polygon;
import com.dexter.labs.digitalfarm.dto.FieldDto;
import com.dexter.labs.digitalfarm.entity.Field;
import com.dexter.labs.digitalfarm.model.Boundary;
import org.springframework.stereotype.Component;

@Component
public class FieldDtoConverter{

    public FieldDto convert(Field field, Polygon polygon) {
        Boundary boundaries = new Boundary(polygon.getId(), polygon.getGeoJson());
        return new FieldDto(
                field.getId(),
                field.getName(),
                field.getCreated(),
                field.getUpdated(),
                field.getCountryCode(),
                boundaries
        );
    }
}
