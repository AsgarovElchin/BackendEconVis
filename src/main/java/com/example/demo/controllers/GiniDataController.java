package com.example.demo.controllers;

import com.example.demo.models.GiniData;
import com.example.demo.services.GiniDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gini")
public class GiniDataController {

    @Autowired
    private GiniDataService giniDataService;

    @GetMapping("/year/{year}")
    public List<GiniData> getGiniDataByYear(@PathVariable int year) {
        return giniDataService.getGiniDataByYear(year);
    }
}