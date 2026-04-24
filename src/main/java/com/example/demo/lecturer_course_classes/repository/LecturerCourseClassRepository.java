// LecturerCourseClassRepository
package com.example.demo.lecturer_course_classes.repository;

import com.example.demo.lecturer_course_classes.model.entity.LecturerCourseClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface LecturerCourseClassRepository extends JpaRepository<LecturerCourseClasses, UUID> {

    // Dung LEFT JOIN FETCH de van doc duoc ban ghi ngay ca khi du lieu lien ket bi
    // thieu.
    @Query("""
                SELECT l FROM LecturerCourseClasses l
                LEFT JOIN FETCH l.employee
                LEFT JOIN FETCH l.courseSection
                WHERE l.isActive = true AND l.deletedAt IS NULL
            """)
    List<LecturerCourseClasses> findAllActive();

}
