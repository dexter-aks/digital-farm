package com.dexter.labs.digitalfarm.client.owm.model;

import java.util.List;
import java.util.Objects;

public class WeatherHistory {

    private long dt;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherHistory)) return false;
        WeatherHistory that = (WeatherHistory) o;
        return dt == that.dt &&
                weather.equals(that.weather) &&
                main.equals(that.main) &&
                wind.equals(that.wind) &&
                clouds.equals(that.clouds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dt, weather, main, wind, clouds);
    }

    @Override
    public String toString() {
        return "WeatherHistory{" +
                "dt=" + dt +
                ", weather=" + weather +
                ", main=" + main +
                ", wind=" + wind +
                ", clouds=" + clouds +
                '}';
    }
}
