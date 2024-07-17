package com.example.demo.services;

import com.example.demo.models.CountryData;
import com.example.demo.repositories.CountryDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CountryDataService {

    @Autowired
    private CountryDataRepository countryDataRepository;

    public Map<String, Object> getCountryData(List<String> countries) {
        List<CountryData> data = countryDataRepository.findByCountryNameIn(countries);
        Map<String, Object> result = new HashMap<>();

        // Process data and structure it as needed
        for (CountryData item : data) {
            Map<String, Object> countryData = (Map<String, Object>) result.computeIfAbsent(item.getCountryName(), k -> new HashMap<String, Object>());
            countryData.put(item.getIndicator() + "_" + item.getYear(), item.getValue());
        }

        return result;
    }
}
