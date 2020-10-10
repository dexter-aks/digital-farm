package com.dexter.labs.digitalfarm.controller;


import com.dexter.labs.digitalfarm.client.owm.PolygonClient;
import com.dexter.labs.digitalfarm.client.owm.model.Polygon;
import com.dexter.labs.digitalfarm.dto.BoundaryRequestDto;
import com.dexter.labs.digitalfarm.dto.FieldDto;
import com.dexter.labs.digitalfarm.service.IFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/field")
public class FieldController {

    @Autowired
    private IFieldService fieldService;

    @GetMapping(path = "/{fieldId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FieldDto> getFieldInfo(@PathVariable(name = "fieldId") String fieldId) throws Exception {
        System.out.println("Calling Get Polygon");
        return ResponseEntity.ok(fieldService.getFieldInfo(fieldId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FieldDto> createBoundary(@RequestBody BoundaryRequestDto boundaryRequestDto) throws Exception {
        System.out.println("Calling Create Polygon");
        return ResponseEntity.ok(fieldService.createField(boundaryRequestDto));
    }
}
