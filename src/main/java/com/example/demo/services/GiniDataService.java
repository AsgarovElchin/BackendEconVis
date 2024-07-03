package com.example.demo.services;

import com.example.demo.models.GiniData;
import com.example.demo.repositories.GiniDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiniDataService {

    @Autowired
    private GiniDataRepository giniDataRepository;

    public List<GiniData> getGiniDataByYear(int year) {
        return giniDataRepository.findByYear(year);
    }
}
