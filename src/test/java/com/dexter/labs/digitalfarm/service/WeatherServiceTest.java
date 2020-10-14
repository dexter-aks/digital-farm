package com.dexter.labs.digitalfarm.service;

import com.dexter.labs.digitalfarm.client.owm.WeatherClient;
import com.dexter.labs.digitalfarm.client.owm.model.WeatherHistory;
import com.dexter.labs.digitalfarm.converter.WeatherDtoConverter;
import com.dexter.labs.digitalfarm.dto.WeatherDetailsDto;
import com.dexter.labs.digitalfarm.dto.WeatherDto;
import com.dexter.labs.digitalfarm.entity.Field;
import com.dexter.labs.digitalfarm.exception.ClientException;
import com.dexter.labs.digitalfarm.exception.FieldNotFoundException;
import com.dexter.labs.digitalfarm.repository.FieldRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class WeatherServiceTest {

   @Mock
   private FieldRepository fieldRepository;

   @Mock
   private WeatherClient weatherClient;

   @Mock
   private WeatherDtoConverter weatherDtoConverter;

    @InjectMocks
    private IWeatherService weatherService;

    String anyId = UUID.randomUUID().toString();
    String anyName = "Rice";
    String anyCountryCode = "DEU";

    @BeforeEach
    public void init(){
        weatherService = new WeatherService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getWeatherDetails_successfully_when_fieldIdIsValid() throws InterruptedException, ClientException, IOException, FieldNotFoundException {
        long anyTimestamp = 128L;
        float anyTemperature = 34.5F;
        int anyHumidity = 23;
        float anyTemperatureMax = 34.5F;
        float anyTemperatureMin = 14.5F;
        Field field = new Field(anyName,anyCountryCode, anyId);
        WeatherDto weatherDto = new WeatherDto(anyTimestamp, anyTemperature, anyHumidity, anyTemperatureMax, anyTemperatureMin);
        List<WeatherDto> weatherDtos = new ArrayList<>();
        weatherDtos.add(weatherDto);
        WeatherDetailsDto weatherDetailsDto = new WeatherDetailsDto(weatherDtos);
        WeatherHistory[] weatherHistories = new WeatherHistory[1];

        when(fieldRepository.findById(any())).thenReturn(Optional.of(field));
        when(weatherClient.getWeatherHistory(any())).thenReturn(weatherHistories);
        when(weatherDtoConverter.convert(any())).thenReturn(weatherDetailsDto);

        WeatherDetailsDto actual = weatherService.getWeatherDetails(anyId);

        assertEquals(1, actual.getWeather().size());
        assertEquals(anyTemperature, actual.getWeather().get(0).getTemperature());
    }
}
