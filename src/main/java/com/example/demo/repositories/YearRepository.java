package com.example.demo.repositories;

import com.example.demo.models.Year;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<Year, Long> {
}
