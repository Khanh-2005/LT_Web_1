package com.example.demo.course_sections.repository;

import com.example.demo.course_sections.model.entity.CourseSections;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseSectionsRepository
        extends JpaRepository<CourseSections, UUID>, JpaSpecificationExecutor<CourseSections> {

    List<CourseSections> findByDeletedAtIsNull();

    Optional<CourseSections> findByIdAndDeletedAtIsNull(UUID id);

    boolean existsByIdAndDeletedAtIsNull(UUID id);

    List<CourseSections> findByCodeContainingIgnoreCase(String code);

    List<CourseSections> findByAcademicYearContainingIgnoreCase(String academicYear);

    List<CourseSections> findByMaxStudents(Integer maxStudents);

    List<CourseSections> findByMinStudents(Integer minStudents);

    List<CourseSections> findByClassTypeContainingIgnoreCase(String classType);

    List<CourseSections> findByStatusContainingIgnoreCase(String status);
}
