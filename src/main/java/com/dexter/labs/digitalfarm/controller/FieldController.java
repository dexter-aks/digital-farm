package com.dexter.labs.digitalfarm.controller;


import com.dexter.labs.digitalfarm.dto.BoundaryRequestDto;
import com.dexter.labs.digitalfarm.dto.FieldDto;
import com.dexter.labs.digitalfarm.dto.WeatherDetailsDto;
import com.dexter.labs.digitalfarm.exception.ClientException;
import com.dexter.labs.digitalfarm.exception.FieldNotFoundException;
import com.dexter.labs.digitalfarm.service.IFieldService;
import com.dexter.labs.digitalfarm.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/field")
public class FieldController {

    @Autowired
    private IFieldService fieldService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping(path = "/{fieldId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FieldDto> getFieldInfo(@PathVariable(name = "fieldId") String fieldId) throws InterruptedException, IOException, FieldNotFoundException, ClientException {
        System.out.println("Calling Get Polygon");
        return ResponseEntity.ok(fieldService.getFieldInfo(fieldId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FieldDto> createField(
            @RequestBody @Validated BoundaryRequestDto boundaryRequestDto) throws InterruptedException, ClientException, IOException {
        System.out.println("Calling Create Polygon");
        return ResponseEntity.ok(fieldService.createField(boundaryRequestDto));
    }

    @PutMapping(path = "/{fieldId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FieldDto> updateField(
            @PathVariable(name = "fieldId") String fieldId,
            @RequestBody BoundaryRequestDto boundaryRequestDto
    ) throws InterruptedException, IOException, FieldNotFoundException, ClientException {
        System.out.println("Calling Update Polygon");
        return ResponseEntity.ok(fieldService.updateField(fieldId, boundaryRequestDto));
    }

    @DeleteMapping(path = "/{fieldId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteField(
            @PathVariable(name = "fieldId") String fieldId
    ) throws FieldNotFoundException, IOException, InterruptedException, ClientException {
        System.out.println("Calling Delete Polygon");
        fieldService.deleteField(fieldId);
        return new ResponseEntity<>(fieldId, HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{fieldId}/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<WeatherDetailsDto> getWeatherHistory(@PathVariable(name = "fieldId") String fieldId) throws InterruptedException, IOException, ClientException, FieldNotFoundException {
        System.out.println("Calling Get Weather History");
        return ResponseEntity.ok(weatherService.getWeatherDetails(fieldId));
    }
}
