package com.dexter.labs.digitalfarm.service;

import com.dexter.labs.digitalfarm.client.owm.PolygonClient;
import com.dexter.labs.digitalfarm.client.owm.model.GeoJson;
import com.dexter.labs.digitalfarm.client.owm.model.Polygon;
import com.dexter.labs.digitalfarm.converter.FieldDtoConverter;
import com.dexter.labs.digitalfarm.converter.FieldInputDtoConverter;
import com.dexter.labs.digitalfarm.dto.BoundaryRequestDto;
import com.dexter.labs.digitalfarm.dto.FieldDto;
import com.dexter.labs.digitalfarm.entity.Field;
import com.dexter.labs.digitalfarm.exception.ClientException;
import com.dexter.labs.digitalfarm.exception.FieldNotFoundException;
import com.dexter.labs.digitalfarm.model.Boundary;
import com.dexter.labs.digitalfarm.repository.FieldRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FieldServiceTest {

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private PolygonClient polygonClient;

    @Mock
    private FieldInputDtoConverter fieldInputDtoConverter;

    @Mock
    private FieldDtoConverter fieldDtoConverter;

    @InjectMocks
    private IFieldService fieldService;

    @Mock
    private Boundary anyBoundary;

    @Mock
    private GeoJson anyGeoJson;

    String anyId = UUID.randomUUID().toString();
    String anyName = "Rice";
    String anyCountryCode = "DEU";
    Instant anyTime = Instant.now();

    @BeforeEach
    public void init(){
        fieldService = new FieldService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getField_successfully_getFieldById() throws InterruptedException, ClientException, IOException, FieldNotFoundException {
        Field field = new Field(anyName,anyCountryCode, anyId);
        Polygon polygon = new Polygon();
        FieldDto fieldDto = new FieldDto(
                anyId,
                anyName,
                anyTime,
                anyTime,
                anyCountryCode,
                anyBoundary
        );
        when(fieldRepository.findById(any())).thenReturn(Optional.of(field));
        when(polygonClient.getPolygonById(any())).thenReturn(polygon);
        when(fieldDtoConverter.convert(any(),any())).thenReturn(fieldDto);

        FieldDto actual = fieldService.getFieldInfo(anyId);
        assertEquals(anyName, actual.getName());
    }

    @Test
    public void createField_successfully_when_request_isValid() throws InterruptedException, ClientException, IOException {
        BoundaryRequestDto boundaryRequestDto = new BoundaryRequestDto(
                anyName,
                anyCountryCode,
                anyGeoJson
        );
        Polygon polygon = new Polygon();
        Field field = new Field(anyName,anyCountryCode, anyId);
        FieldDto fieldDto = new FieldDto(
                anyId,
                anyName,
                anyTime,
                anyTime,
                anyCountryCode,
                anyBoundary
        );
        when(polygonClient.createPolygon(any())).thenReturn(polygon);
        when(fieldInputDtoConverter.convert(any(), any())).thenReturn(field);
        when(fieldRepository.save(any())).thenReturn(field);
        when(fieldDtoConverter.convert(any(), any())).thenReturn(fieldDto);

        FieldDto actual = fieldService.createField(boundaryRequestDto);
        assertEquals(anyName, actual.getName());
        assertEquals(anyCountryCode, actual.getCountryCode());
    }

    @Test
    public void updateField_successfully_when_request_isValid() throws InterruptedException, ClientException, IOException, FieldNotFoundException {
        Field field = new Field(anyName,anyCountryCode, anyId);
        Polygon polygon = new Polygon();
        BoundaryRequestDto boundaryRequestDto = new BoundaryRequestDto(
                anyName,
                anyCountryCode,
                anyGeoJson
        );
        FieldDto fieldDto = new FieldDto(
                anyId,
                anyName,
                anyTime,
                anyTime,
                anyCountryCode,
                anyBoundary
        );
        when(fieldRepository.findById(any())).thenReturn(Optional.of(field));
        when(polygonClient.updatePolygon(any(), any())).thenReturn(polygon);
        when(fieldRepository.save(any())).thenReturn(field);
        when(fieldDtoConverter.convert(any(), any())).thenReturn(fieldDto);

        FieldDto actual = fieldService.updateField(anyId, boundaryRequestDto);
        assertEquals(anyName, actual.getName());
        assertEquals(anyCountryCode, actual.getCountryCode());
    }

    @Test
    public void deleteField_successfully_when_fieldIdExists() throws InterruptedException, ClientException, IOException, FieldNotFoundException {
        Field field = new Field(anyName,anyCountryCode, anyId);
        when(fieldRepository.findById(any())).thenReturn(Optional.of(field));

        fieldService.deleteField(anyId);

        verify(polygonClient).deletePolygon(anyId);
        verify(fieldRepository).deleteById(anyId);
    }
}
