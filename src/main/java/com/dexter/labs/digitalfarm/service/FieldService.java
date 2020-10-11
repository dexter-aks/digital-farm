package com.dexter.labs.digitalfarm.service;

import com.dexter.labs.digitalfarm.client.owm.PolygonClient;
import com.dexter.labs.digitalfarm.client.owm.model.Polygon;
import com.dexter.labs.digitalfarm.converter.FieldDtoConverter;
import com.dexter.labs.digitalfarm.converter.FieldInputDtoConverter;
import com.dexter.labs.digitalfarm.dto.BoundaryRequestDto;
import com.dexter.labs.digitalfarm.dto.FieldDto;
import com.dexter.labs.digitalfarm.entity.Field;
import com.dexter.labs.digitalfarm.exception.ClientException;
import com.dexter.labs.digitalfarm.exception.FieldNotFoundException;
import com.dexter.labs.digitalfarm.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;

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

    public FieldDto getFieldInfo(String fieldId)
            throws InterruptedException, ClientException, IOException, FieldNotFoundException {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException(fieldId));

        String boundaryId = field.getBoundaryId();
        Polygon polygon = polygonClient.getPolygonById(boundaryId);
        return fieldDtoConverter.convert(field, polygon);
    }

    public FieldDto createField(BoundaryRequestDto boundaryRequestDto)
            throws InterruptedException, ClientException, IOException {
        Polygon polygon = polygonClient.createPolygon(boundaryRequestDto);
        Field fieldInput = fieldInputDtoConverter.convert(polygon, boundaryRequestDto.getCountryCode());
        Field field = fieldRepository.save(fieldInput);
        return fieldDtoConverter.convert(field, polygon);
    }

    public FieldDto updateField(String fieldId, BoundaryRequestDto boundaryRequestDto)
            throws InterruptedException, ClientException, IOException, FieldNotFoundException {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException(fieldId));

        String boundaryId = field.getBoundaryId();
        Polygon polygon = polygonClient.updatePolygon(boundaryId, boundaryRequestDto);
        String name = boundaryRequestDto.getName();
        String countryCode = boundaryRequestDto.getCountryCode();
        String currentCountryCode = field.getCountryCode();
        if( name != null && !name.isEmpty() && !field.getName().equals(name)){
            field.setName(boundaryRequestDto.getName());
        }
        if(countryCode != null && !countryCode.isEmpty() && currentCountryCode!= null && !currentCountryCode.equals(countryCode)){
            field.setCountryCode(boundaryRequestDto.getCountryCode());
        }
        field.setUpdated(Instant.now());
        Field latestField = fieldRepository.save(field);

        return fieldDtoConverter.convert(latestField, polygon);
    }

    public void deleteField(String fieldId) throws FieldNotFoundException, InterruptedException, ClientException, IOException {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException(fieldId));

        polygonClient.deletePolygon(field.getBoundaryId());

        fieldRepository.deleteById(fieldId);
    }
}
