package com.example.demo.services;

import com.example.demo.models.Report;
import com.example.demo.models.Country;
import com.example.demo.models.Indicator;
import com.example.demo.models.Year;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.IndicatorRepository;
import com.example.demo.repositories.ReportRepository;
import com.example.demo.repositories.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private YearRepository yearRepository;

    public List<Report> getReports(List<Long> countryIds, List<Long> indicatorIds, List<Long> yearIds) {
        List<Country> countries = countryRepository.findAllById(countryIds);
        List<Indicator> indicators = indicatorRepository.findAllById(indicatorIds);
        List<Year> years = yearRepository.findAllById(yearIds);
        return reportRepository.findByCountryInAndIndicatorInAndYearIn(countries, indicators, years);
    }

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    public List<Indicator> getIndicators() {
        return indicatorRepository.findAll();
    }

    public List<Year> getYears() {
        return yearRepository.findAll();
    }
}
