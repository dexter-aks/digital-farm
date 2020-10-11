package com.dexter.labs.digitalfarm.client.owm.model;

import java.util.Objects;

public class Main {

    private float temp;
    private int pressure;
    private int humidity;
    private float temp_min;
    private float temp_max;

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Main)) return false;
        Main main = (Main) o;
        return Float.compare(main.temp, temp) == 0 &&
                pressure == main.pressure &&
                humidity == main.humidity &&
                Float.compare(main.temp_min, temp_min) == 0 &&
                Float.compare(main.temp_max, temp_max) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temp, pressure, humidity, temp_min, temp_max);
    }

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                '}';
    }
}
