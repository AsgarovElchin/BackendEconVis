package com.example.demo.controllers;


import com.example.demo.services.CountryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/countrydata")
public class CountryDataController {

    @Autowired
    private CountryDataService countryDataService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCountryData(@RequestParam Long countryId) {
        Map<String, Object> data = countryDataService.getCountryData(countryId);
        return ResponseEntity.ok(data);
    }
}