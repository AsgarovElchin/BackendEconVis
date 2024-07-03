package com.example.demo.repositories;


import com.example.demo.models.GiniData;
import com.example.demo.models.GiniDataId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiniDataRepository extends JpaRepository<GiniData, GiniDataId> {
    List<GiniData> findByYear(int year);
}