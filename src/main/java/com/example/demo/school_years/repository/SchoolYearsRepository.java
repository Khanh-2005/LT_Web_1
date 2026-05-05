package com.example.demo.school_years.repository;

import com.example.demo.school_years.model.entity.SchoolYears;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolYearsRepository extends JpaRepository<SchoolYears, String> {
}