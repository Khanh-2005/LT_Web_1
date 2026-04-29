package com.example.demo.lecturer_course_classes.repository;

import com.example.demo.lecturer_course_classes.model.entity.LecturerCourseClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LecturerCourseClassRepository extends JpaRepository<LecturerCourseClasses, UUID> {

    @Query("""
                SELECT l FROM LecturerCourseClasses l
                LEFT JOIN FETCH l.employee
                LEFT JOIN FETCH l.courseSection
                WHERE l.isActive = true AND l.deletedAt IS NULL
            """)
    List<LecturerCourseClasses> findAllActive();

    @Query("""
                SELECT l FROM LecturerCourseClasses l
                LEFT JOIN FETCH l.employee
                LEFT JOIN FETCH l.courseSection
                WHERE l.id = :id AND l.isActive = true AND l.deletedAt IS NULL
            """)
    Optional<LecturerCourseClasses> findActiveById(@Param("id") UUID id);

    @Query("""
                SELECT COUNT(l) > 0 FROM LecturerCourseClasses l
                WHERE l.employeeId = :employeeId
                AND l.courseClassId = :courseClassId
                AND l.isActive = true AND l.deletedAt IS NULL
            """)
    boolean existsActiveByEmployeeAndCourseClass(
        @Param("employeeId") UUID employeeId,
        @Param("courseClassId") UUID courseClassId
    );
}