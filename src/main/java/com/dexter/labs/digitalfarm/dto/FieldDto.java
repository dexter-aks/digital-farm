package com.dexter.labs.digitalfarm.dto;

import com.dexter.labs.digitalfarm.model.Boundary;

import java.time.Instant;
import java.util.Objects;

public class FieldDto {

    private String id;
    private String name;
    private Instant created;
    private Instant updated;
    private String countryCode;
    private Boundary boundary;

    public FieldDto(String id, String name, Instant created, Instant updated, String countryCode, Boundary boundary) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.countryCode = countryCode;
        this.boundary = boundary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Boundary getBoundary() {
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldDto)) return false;
        FieldDto fieldDto = (FieldDto) o;
        return id.equals(fieldDto.id) &&
                name.equals(fieldDto.name) &&
                created.equals(fieldDto.created) &&
                updated.equals(fieldDto.updated) &&
                Objects.equals(countryCode, fieldDto.countryCode) &&
                boundary.equals(fieldDto.boundary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, created, updated, countryCode, boundary);
    }

    @Override
    public String toString() {
        return "FieldDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", countryCode='" + countryCode + '\'' +
                ", boundaries=" + boundary +
                '}';
    }
}
