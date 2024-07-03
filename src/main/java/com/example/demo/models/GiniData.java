package com.example.demo.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "gini_data")
@IdClass(GiniDataId.class)
public class GiniData {

    @Id
    private String iso;

    @Id
    private int year;

    private double giniIndex;

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

    public double getGiniIndex() {
        return giniIndex;
    }

    public void setGiniIndex(double giniIndex) {
        this.giniIndex = giniIndex;
    }
}
