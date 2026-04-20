package com.example.demo.student_course_sections.service;

import com.example.demo.student_course_sections.model.entity.StudentCourseSections;
import com.example.demo.student_course_sections.repository.StudentCourseSectionsRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseSectionsService {
    private final StudentCourseSectionsRepository repo;

    public StudentCourseSectionsService(StudentCourseSectionsRepository repo) {
        this.repo = repo;
    }

    public List<StudentCourseSections> getAll() {
        return repo.findAllWithDetails();
    }

    public StudentCourseSections getById(UUID id) {
        return repo.findByIdAndDeletedAtIsNullWithDetails(id).orElse(null);
    }

    public StudentCourseSections create(StudentCourseSections studentCourseSection) {
        LocalDateTime now = LocalDateTime.now();
        studentCourseSection.setId(null);
        studentCourseSection.setDeletedAt(null);
        studentCourseSection.setDeletedBy(null);
        studentCourseSection.setUpdatedAt(now);
        if (studentCourseSection.getCreatedAt() == null) {
            studentCourseSection.setCreatedAt(now);
        }
        if (studentCourseSection.getIsActive() == null) {
            studentCourseSection.setIsActive(true);
        }
        return repo.save(studentCourseSection);
    }

    public StudentCourseSections update(UUID id, StudentCourseSections studentCourseSection) {
        StudentCourseSections old = getById(id);
        if (old == null) {
            return null;
        }

        old.setStudentId(studentCourseSection.getStudentId());
        old.setCourseSectionId(studentCourseSection.getCourseSectionId());
        old.setStatus(studentCourseSection.getStatus());
        old.setRegisteredAt(studentCourseSection.getRegisteredAt());
        old.setNote(studentCourseSection.getNote());
        old.setUpdatedAt(LocalDateTime.now());
        old.setUpdatedBy(studentCourseSection.getUpdatedBy());
        old.setIsActive(
                studentCourseSection.getIsActive() != null ? studentCourseSection.getIsActive() : old.getIsActive());

        if (old.getCreatedAt() == null) {
            old.setCreatedAt(studentCourseSection.getCreatedAt());
        }
        if (old.getCreatedBy() == null) {
            old.setCreatedBy(studentCourseSection.getCreatedBy());
        }

        return repo.save(old);
    }

    public void softDelete(UUID id) {
        StudentCourseSections studentCourseSection = getById(id);
        if (studentCourseSection == null) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        studentCourseSection.setDeletedAt(now);
        studentCourseSection.setIsActive(false);
        studentCourseSection.setUpdatedAt(now);
        repo.save(studentCourseSection);
    }

    public List<StudentCourseSections> search(UUID studentId, UUID courseSectionId, String keyword, String status,
            Boolean isActive) {
        return repo.findAll(buildSearchSpecification(studentId, courseSectionId, keyword, status, isActive));
    }

    public Page<StudentCourseSections> searchPaged(UUID studentId, UUID courseSectionId, String keyword, String status,
            Boolean isActive, int page, int size) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.max(size, 1),
                Sort.by(Sort.Direction.DESC, "createdAt"));
        return repo.findAll(buildSearchSpecification(studentId, courseSectionId, keyword, status, isActive), pageable);
    }

    private boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private Specification<StudentCourseSections> buildSearchSpecification(UUID studentId, UUID courseSectionId,
            String keyword, String status, Boolean isActive) {
        Specification<StudentCourseSections> spec = (root, query, cb) -> cb.isNull(root.get("deletedAt"));

        if (studentId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("studentId"), studentId));
        }

        if (courseSectionId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("courseSectionId"), courseSectionId));
        }

        if (hasText(keyword)) {
            String pattern = "%" + keyword.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.or(
                    cb.like(cb.lower(root.get("status")), pattern),
                    cb.like(cb.lower(root.get("note")), pattern),
                    cb.like(cb.lower(root.get("studentId").as(String.class)), pattern),
                    cb.like(cb.lower(root.get("courseSectionId").as(String.class)), pattern)));
        }

        if (hasText(status)) {
            String pattern = "%" + status.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("status")), pattern));
        }

        if (isActive != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("isActive"), isActive));
        }

        return spec;
    }
}