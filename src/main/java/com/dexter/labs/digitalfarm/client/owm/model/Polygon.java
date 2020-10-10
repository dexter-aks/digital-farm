package com.dexter.labs.digitalfarm.client.owm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties
public class Polygon {

    private String id;
    @JsonProperty("geo_json")
    private GeoJson geoJson;
    private String name;
    private List<Float> center;
    private Float area;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("created_at")
    private long createdAt;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Float> getCenter() {
        return center;
    }

    public void setCenter(List<Float> center) {
        this.center = center;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polygon)) return false;
        Polygon polygon = (Polygon) o;
        return createdAt == polygon.createdAt &&
                Objects.equals(id, polygon.id) &&
                Objects.equals(geoJson, polygon.geoJson) &&
                Objects.equals(name, polygon.name) &&
                Objects.equals(center, polygon.center) &&
                Objects.equals(area, polygon.area) &&
                Objects.equals(userId, polygon.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, geoJson, name, center, area, userId, createdAt);
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "id='" + id + '\'' +
                ", geoJson=" + geoJson +
                ", name='" + name + '\'' +
                ", center=" + center +
                ", area=" + area +
                ", userId='" + userId + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
