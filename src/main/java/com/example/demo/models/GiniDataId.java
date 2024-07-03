package com.example.demo.models;

import java.io.Serializable;
import java.util.Objects;

public class GiniDataId implements Serializable {
    private String iso;
    private int year;

    // Default constructor
    public GiniDataId() {}

    // Parameterized constructor
    public GiniDataId(String iso, int year) {
        this.iso = iso;
        this.year = year;
    }

    // Getters and setters
    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // hashCode and equals methods
    @Override
    public int hashCode() {
        return Objects.hash(iso, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GiniDataId that = (GiniDataId) obj;
        return year == that.year && Objects.equals(iso, that.iso);
    }
}
