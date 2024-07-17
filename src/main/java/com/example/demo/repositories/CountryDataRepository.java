package com.example.demo.repositories;

import com.example.demo.models.CountryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CountryDataRepository extends JpaRepository<CountryData, Long> {

    List<CountryData> findByCountryNameIn(List<String> countries);
}