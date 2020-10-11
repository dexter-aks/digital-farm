package com.dexter.labs.digitalfarm.service;

import com.dexter.labs.digitalfarm.client.owm.WeatherClient;
import com.dexter.labs.digitalfarm.client.owm.model.WeatherHistory;
import com.dexter.labs.digitalfarm.converter.WeatherDtoConverter;
import com.dexter.labs.digitalfarm.dto.WeatherDetailsDto;
import com.dexter.labs.digitalfarm.entity.Field;
import com.dexter.labs.digitalfarm.exception.ClientException;
import com.dexter.labs.digitalfarm.exception.FieldNotFoundException;
import com.dexter.labs.digitalfarm.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService implements IWeatherService{

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private WeatherDtoConverter weatherDtoConverter;

    @Override
    public WeatherDetailsDto getWeatherDetails(String fieldId) throws ClientException, FieldNotFoundException, IOException, InterruptedException {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException(fieldId));

        String boundaryId = field.getBoundaryId();
        WeatherHistory[] weatherHistories = weatherClient.getWeatherHistory(boundaryId);

        WeatherDetailsDto weatherDetailsDto = weatherDtoConverter.convert(weatherHistories);

        System.out.println("weatherDetailsDto:"+weatherDetailsDto);

        return weatherDetailsDto;
    }
}
