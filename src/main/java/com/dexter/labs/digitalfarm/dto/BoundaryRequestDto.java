package com.dexter.labs.digitalfarm.dto;

import com.dexter.labs.digitalfarm.client.owm.model.GeoJson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoundaryRequestDto {

    private String name;

    private String countryCode;

    @JsonProperty("geo_json")
    private GeoJson geoJson;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
        return Objects.equals(name, that.name) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(geoJson, that.geoJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countryCode, geoJson);
    }

    @Override
    public String toString() {
        return "BoundaryRequestDto{" +
                "name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", geoJson=" + geoJson +
                '}';
    }
}
