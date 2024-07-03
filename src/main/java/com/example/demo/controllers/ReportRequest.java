package com.example.demo.controllers;

import java.util.List;

public class ReportRequest {
    private List<Long> countryIds;
    private List<Long> indicatorIds;
    private List<Long> yearIds;

    // Getters and Setters
    public List<Long> getCountryIds() {
        return countryIds;
    }

    public void setCountryIds(List<Long> countryIds) {
        this.countryIds = countryIds;
    }

    public List<Long> getIndicatorIds() {
        return indicatorIds;
    }

    public void setIndicatorIds(List<Long> indicatorIds) {
        this.indicatorIds = indicatorIds;
    }

    public List<Long> getYearIds() {
        return yearIds;
    }

    public void setYearIds(List<Long> yearIds) {
        this.yearIds = yearIds;
    }
}
