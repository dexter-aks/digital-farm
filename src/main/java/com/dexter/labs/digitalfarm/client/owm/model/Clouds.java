package com.dexter.labs.digitalfarm.client.owm.model;

import java.util.Objects;

public class Clouds {

    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clouds)) return false;
        Clouds clouds = (Clouds) o;
        return all == clouds.all;
    }

    @Override
    public int hashCode() {
        return Objects.hash(all);
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
