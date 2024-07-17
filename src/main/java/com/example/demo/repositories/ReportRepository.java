package com.example.demo.repositories;

import com.example.demo.models.Report;
import com.example.demo.models.Country;
import com.example.demo.models.Indicator;
import com.example.demo.models.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByCountryInAndIndicatorInAndYearIn(List<Country> countries, List<Indicator> indicators, List<Year> years);
    List<Report> findByCountryId(Long countryId);
}