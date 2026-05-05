package com.example.demo.academic_years.repository;

import com.example.demo.academic_years.model.entity.AcademicYears;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicYearsRepository extends JpaRepository<AcademicYears, String> {
}