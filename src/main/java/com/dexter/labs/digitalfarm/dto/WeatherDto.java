package com.dexter.labs.digitalfarm.dto;

import java.util.Objects;

public class WeatherDto {
    private long timestamp;
    private float temperature;
    private int humidity;
    private float temperatureMax;
    private float temperatureMin;

    public WeatherDto(long timestamp, float temperature, int humidity, float temperatureMax, float temperatureMin) {
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.humidity = humidity;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public float getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherDto)) return false;
        WeatherDto that = (WeatherDto) o;
        return timestamp == that.timestamp &&
                Float.compare(that.temperature, temperature) == 0 &&
                humidity == that.humidity &&
                Float.compare(that.temperatureMax, temperatureMax) == 0 &&
                Float.compare(that.temperatureMin, temperatureMin) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, temperature, humidity, temperatureMax, temperatureMin);
    }

    @Override
    public String toString() {
        return "WeatherDto{" +
                "timestamp=" + timestamp +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", temperatureMax=" + temperatureMax +
                ", temperatureMin=" + temperatureMin +
                '}';
    }
}
