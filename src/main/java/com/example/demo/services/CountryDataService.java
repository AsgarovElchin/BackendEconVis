package com.example.demo.services;

import com.example.demo.models.CountryData;
import com.example.demo.models.Report;
import com.example.demo.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CountryDataService {

    @Autowired
    private ReportRepository reportRepository;

    public Map<String, Object> getCountryData(Long countryId) {
        List<Report> reports = reportRepository.findByCountryId(countryId);
        Map<String, Object> result = new HashMap<>();

        for (Report report : reports) {
            Map<String, Object> indicatorData = (Map<String, Object>) result.computeIfAbsent(report.getIndicator().getName(), k -> new HashMap<String, Object>());
            indicatorData.put(String.valueOf(report.getYear().getYear()), report.getData());
        }

        return result;
    }
}