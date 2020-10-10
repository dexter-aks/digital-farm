package com.dexter.labs.digitalfarm.model;

import com.dexter.labs.digitalfarm.client.owm.model.GeoJson;

import java.util.Objects;

public class Boundary {

    private String id;
    private GeoJson geoJson;

    public Boundary(String id, GeoJson geoJson) {
        this.id = id;
        this.geoJson = geoJson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (!(o instanceof Boundary)) return false;
        Boundary boundary = (Boundary) o;
        return id.equals(boundary.id) &&
                geoJson.equals(boundary.geoJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, geoJson);
    }

    @Override
    public String toString() {
        return "Boundary{" +
                "id=" + id +
                ", geoJson=" + geoJson +
                '}';
    }
}
