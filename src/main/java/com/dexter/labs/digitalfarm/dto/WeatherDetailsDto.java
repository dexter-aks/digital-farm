package com.dexter.labs.digitalfarm.dto;

import java.util.List;
import java.util.Objects;

public class WeatherDetailsDto {
    private List<WeatherDto> weather;

    public WeatherDetailsDto(List<WeatherDto> weather) {
        this.weather = weather;
    }

    public List<WeatherDto> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDto> weather) {
        this.weather = weather;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherDetailsDto)) return false;
        WeatherDetailsDto that = (WeatherDetailsDto) o;
        return Objects.equals(weather, that.weather);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weather);
    }

    @Override
    public String toString() {
        return "WeatherDetailsDto{" +
                "weather=" + weather +
                '}';
    }
}


