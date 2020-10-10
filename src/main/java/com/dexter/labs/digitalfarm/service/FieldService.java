package com.dexter.labs.digitalfarm.service;

import com.dexter.labs.digitalfarm.client.owm.PolygonClient;
import com.dexter.labs.digitalfarm.client.owm.model.Polygon;
import com.dexter.labs.digitalfarm.converter.FieldDtoConverter;
import com.dexter.labs.digitalfarm.converter.FieldInputDtoConverter;
import com.dexter.labs.digitalfarm.dto.BoundaryRequestDto;
import com.dexter.labs.digitalfarm.dto.FieldDto;
import com.dexter.labs.digitalfarm.entity.Field;
import com.dexter.labs.digitalfarm.exception.ClientException;
import com.dexter.labs.digitalfarm.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class FieldService implements IFieldService{

    @Autowired
    private PolygonClient polygonClient;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FieldInputDtoConverter fieldInputDtoConverter;

    @Autowired
    private FieldDtoConverter fieldDtoConverter;

    public FieldDto getFieldInfo(String fieldId) throws Exception {
        Optional<Field> fieldOptional = fieldRepository.findById(fieldId);
        if(fieldOptional.isEmpty()) throw new Exception("");
        Field field = fieldOptional.get();
        String boundaryId = field.getBoundaryId();
        Polygon polygon = polygonClient.getPolygonById(boundaryId);
        return fieldDtoConverter.convert(field, polygon);
    }

    public FieldDto createField(BoundaryRequestDto boundaryRequestDto) throws InterruptedException, ClientException, IOException {
        Polygon polygon = polygonClient.createPolygon(boundaryRequestDto);
        Field fieldInput = fieldInputDtoConverter.convert(polygon);
        Field field = fieldRepository.save(fieldInput);
        System.out.println("Field:"+field);
        return fieldDtoConverter.convert(field, polygon);
    }
}
