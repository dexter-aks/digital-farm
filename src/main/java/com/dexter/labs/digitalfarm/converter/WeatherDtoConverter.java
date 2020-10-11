package com.dexter.labs.digitalfarm.converter;

import com.dexter.labs.digitalfarm.client.owm.model.WeatherHistory;
import com.dexter.labs.digitalfarm.dto.WeatherDetailsDto;
import com.dexter.labs.digitalfarm.dto.WeatherDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class WeatherDtoConverter {

    public WeatherDetailsDto convert(WeatherHistory[] weatherHistories) {
        return new WeatherDetailsDto(Arrays.stream(weatherHistories)
                .map(weatherHistory -> new WeatherDto(
                        weatherHistory.getDt(),
                        weatherHistory.getMain().getTemp(),
                        weatherHistory.getMain().getHumidity(),
                        weatherHistory.getMain().getTemp_max(),
                        weatherHistory.getMain().getTemp_min()
                )).collect(Collectors.toList()));
    }
}
