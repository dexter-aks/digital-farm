package com.dexter.labs.digitalfarm.service;

import com.dexter.labs.digitalfarm.dto.BoundaryRequestDto;
import com.dexter.labs.digitalfarm.dto.FieldDto;

public interface IFieldService {

    FieldDto getFieldInfo(String fieldId) throws Exception;

    FieldDto createField(BoundaryRequestDto boundaryRequestDto) throws Exception;
}
