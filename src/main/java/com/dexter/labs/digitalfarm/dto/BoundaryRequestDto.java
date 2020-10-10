package com.dexter.labs.digitalfarm.dto;

import com.dexter.labs.digitalfarm.client.owm.model.GeoJson;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BoundaryRequestDto {

    private String name;

    @JsonProperty("geo_json")
    private GeoJson geoJson;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoJson getGeoJson() {
        return geoJson;
    }

    public void setGeoJson(GeoJson geoJson) {
        this.geoJson = geoJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoundaryRequestDto)) return false;
        BoundaryRequestDto that = (BoundaryRequestDto) o;
        return name.equals(that.name) &&
                geoJson.equals(that.geoJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, geoJson);
    }

    @Override
    public String toString() {
        return "BoundaryRequestDto{" +
                "name='" + name + '\'' +
                ", geoJson=" + geoJson +
                '}';
    }
}
