package com.example.demo.courses.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.courses.model.entity.Courses;

public interface CoursesRepository extends JpaRepository<Courses, UUID> {
    List<Courses> findByDeletedAtIsNull();

    List<Courses> findByDeletedAtIsNullAndIsActive(Boolean isActive);

    List<Courses> findByDeletedAtIsNullAndCourseTypeIgnoreCase(String courseType);

    long countByDeletedAtIsNull();
}
