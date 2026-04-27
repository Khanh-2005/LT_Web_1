package com.example.demo.semesters.repository;

import com.example.demo.semesters.model.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester, String> {

    List<Semester> findBySchoolYearId(String schoolYearId);
}