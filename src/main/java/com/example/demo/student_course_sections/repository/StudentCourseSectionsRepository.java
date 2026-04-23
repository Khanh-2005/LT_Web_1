package com.example.demo.student_course_sections.repository;

import com.example.demo.student_course_sections.model.entity.StudentCourseSections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentCourseSectionsRepository
        extends JpaRepository<StudentCourseSections, UUID>, JpaSpecificationExecutor<StudentCourseSections> {

    @Override
    @EntityGraph(attributePaths = { "student", "courseSection" })
    List<StudentCourseSections> findAll(org.springframework.data.jpa.domain.Specification<StudentCourseSections> spec);

    @Override
    @EntityGraph(attributePaths = { "student", "courseSection" })
    Page<StudentCourseSections> findAll(org.springframework.data.jpa.domain.Specification<StudentCourseSections> spec,
            Pageable pageable);

    List<StudentCourseSections> findByDeletedAtIsNull();

    Optional<StudentCourseSections> findByIdAndDeletedAtIsNull(UUID id);

    boolean existsByIdAndDeletedAtIsNull(UUID id);

    List<StudentCourseSections> findByStudentId(UUID studentId);

    List<StudentCourseSections> findByCourseSectionId(UUID courseSectionId);

    List<StudentCourseSections> findByStatusContainingIgnoreCase(String status);

    @Query("select s from StudentCourseSections s "
            + "left join fetch s.student "
            + "left join fetch s.courseSection "
            + "where s.deletedAt is null")
    List<StudentCourseSections> findAllWithDetails();

    @Query("select s from StudentCourseSections s "
            + "left join fetch s.student "
            + "left join fetch s.courseSection "
            + "where s.id = :id and s.deletedAt is null")
    Optional<StudentCourseSections> findByIdAndDeletedAtIsNullWithDetails(@Param("id") UUID id);

}
