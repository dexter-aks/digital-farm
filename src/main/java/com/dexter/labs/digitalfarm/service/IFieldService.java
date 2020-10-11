package com.dexter.labs.digitalfarm.service;

import com.dexter.labs.digitalfarm.dto.BoundaryRequestDto;
import com.dexter.labs.digitalfarm.dto.FieldDto;
import com.dexter.labs.digitalfarm.exception.ClientException;
import com.dexter.labs.digitalfarm.exception.FieldNotFoundException;

import java.io.IOException;

public interface IFieldService {

    FieldDto getFieldInfo(String fieldId) throws InterruptedException, ClientException, IOException, FieldNotFoundException;

    FieldDto createField(BoundaryRequestDto boundaryRequestDto) throws InterruptedException, ClientException, IOException;

    FieldDto updateField(String fieldId, BoundaryRequestDto boundaryRequestDto) throws InterruptedException, ClientException, IOException, FieldNotFoundException;

    void deleteField(String fieldId) throws FieldNotFoundException, InterruptedException, ClientException, IOException;
}
