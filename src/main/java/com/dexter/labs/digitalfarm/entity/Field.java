package com.dexter.labs.digitalfarm.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "field")
public class Field {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created")
    private Instant created;

    @Column(name = "updated")
    private Instant updated;

    @Column(name = "country_code", unique = true)
    private String countryCode;

    @Column(name = "boundary_id", nullable = false, unique = true)
    private String boundaryId;

    public Field(){}

    public Field(String name, String countryCode, String boundaryId) {
        this.name = name;
        this.created = Instant.now();
        this.updated = Instant.now();
        this.countryCode = countryCode;
        this.boundaryId = boundaryId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getCreated() {
        return created;
    }

    public Instant getUpdated() {
        return updated;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getBoundaryId() {
        return boundaryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setBoundaryId(String boundaryId) {
        this.boundaryId = boundaryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Field)) return false;
        Field field = (Field) o;
        return id.equals(field.id) &&
                name.equals(field.name) &&
                created.equals(field.created) &&
                updated.equals(field.updated) &&
                Objects.equals(countryCode, field.countryCode) &&
                boundaryId.equals(field.boundaryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, created, updated, countryCode, boundaryId);
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", countryCode='" + countryCode + '\'' +
                ", boundaryId=" + boundaryId +
                '}';
    }
}
