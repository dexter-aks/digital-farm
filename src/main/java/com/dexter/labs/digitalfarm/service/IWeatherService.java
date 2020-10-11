package com.dexter.labs.digitalfarm.service;

import com.dexter.labs.digitalfarm.dto.WeatherDetailsDto;
import com.dexter.labs.digitalfarm.exception.ClientException;
import com.dexter.labs.digitalfarm.exception.FieldNotFoundException;

import java.io.IOException;

public interface IWeatherService {
    WeatherDetailsDto getWeatherDetails(String fieldId) throws ClientException, FieldNotFoundException, IOException, InterruptedException;
}
