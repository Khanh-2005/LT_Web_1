package com.example.demo.semesters.repository;

import com.example.demo.semesters.model.entity.Semesters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemestersRepository extends JpaRepository<Semesters, String> {

    List<Semesters> findBySchoolYearId(String schoolYearId);
}