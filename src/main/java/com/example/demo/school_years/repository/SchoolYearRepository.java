package com.example.demo.school_years.repository;

import com.example.demo.school_years.model.entity.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolYearRepository extends JpaRepository<SchoolYear, String> {
}