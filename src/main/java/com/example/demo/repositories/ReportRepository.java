package com.example.demo.repositories;

import com.example.demo.models.Report;
import com.example.demo.models.Country;
import com.example.demo.models.Indicator;
import com.example.demo.models.Year;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByCountryInAndIndicatorInAndYearIn(List<Country> countries, List<Indicator> indicators, List<Year> years);
}
