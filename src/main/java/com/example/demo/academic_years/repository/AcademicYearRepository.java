package com.example.demo.academic_years.repository;

import com.example.demo.academic_years.model.entity.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, String> {
}