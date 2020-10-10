package com.dexter.labs.digitalfarm.client.owm.model;

import java.util.Objects;

public class GeoJson {

    private String type;
    private Object properties;
    private Geometry geometry;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoJson)) return false;
        GeoJson geoJson = (GeoJson) o;
        return type.equals(geoJson.type) &&
                Objects.equals(properties, geoJson.properties) &&
                geometry.equals(geoJson.geometry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, properties, geometry);
    }

    @Override
    public String toString() {
        return "GeoJson{" +
                "type='" + type + '\'' +
                ", properties=" + properties +
                ", geometry=" + geometry +
                '}';
    }
}
