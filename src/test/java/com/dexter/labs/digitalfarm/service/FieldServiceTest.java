package com.dexter.labs.digitalfarm.service;

import com.dexter.labs.digitalfarm.client.owm.PolygonClient;
import com.dexter.labs.digitalfarm.converter.FieldDtoConverter;
import com.dexter.labs.digitalfarm.converter.FieldInputDtoConverter;
import com.dexter.labs.digitalfarm.entity.Field;
import com.dexter.labs.digitalfarm.repository.FieldRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
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
    private Field field;

    @BeforeTestExecution
    public void init(){
        fieldService = new FieldService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getField_successfully_getFieldById(){
        when(fieldRepository.findById(any())).thenReturn(Optional.of(field));

    }
}
