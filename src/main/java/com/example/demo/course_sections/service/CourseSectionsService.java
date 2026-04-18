package com.example.demo.course_sections.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.course_sections.model.entity.CourseSections;
import com.example.demo.course_sections.repository.CourseSectionsRepository;

@Service
public class CourseSectionsService {
    private final CourseSectionsRepository repo;

    public CourseSectionsService(CourseSectionsRepository repo) {
        this.repo = repo;
    }

    // 1. Get all course sections
    public List<CourseSections> getAll() {
        return repo.findByDeletedAtIsNull();
    }

    // 2. Get course section by ID
    public CourseSections getById(UUID id) {
        return repo.findByIdAndDeletedAtIsNull(id).orElse(null);
    }

    // 3. Create new course section
    public CourseSections create(CourseSections courseSection) {
        LocalDateTime now = LocalDateTime.now();
        courseSection.setId(null);
        courseSection.setDeletedAt(null);
        courseSection.setDeletedBy(null);
        courseSection.setUpdatedAt(now);
        if (courseSection.getCreatedAt() == null) {
            courseSection.setCreatedAt(now);
        }
        if (courseSection.getIsActive() == null) {
            courseSection.setIsActive(true);
        }
        return repo.save(courseSection);
    }

    // 4. Update existing course section
    public CourseSections update(UUID id, CourseSections courseSection) {
        CourseSections old = getById(id);
        if (old == null) {
            return null;
        }

        old.setCode(courseSection.getCode());
        old.setCourseId(courseSection.getCourseId());
        old.setSemesterId(courseSection.getSemesterId());
        old.setAcademicYear(courseSection.getAcademicYear());
        old.setEmployeeId(courseSection.getEmployeeId());
        old.setRoomId(courseSection.getRoomId());
        old.setBuildingId(courseSection.getBuildingId());
        old.setMaxStudents(courseSection.getMaxStudents());
        old.setMinStudents(courseSection.getMinStudents());
        old.setClassType(courseSection.getClassType());
        old.setStatus(courseSection.getStatus());
        old.setRegistrationStart(courseSection.getRegistrationStart());
        old.setRegistrationEnd(courseSection.getRegistrationEnd());
        old.setNote(courseSection.getNote());
        old.setUpdatedAt(LocalDateTime.now());
        old.setUpdatedBy(courseSection.getUpdatedBy());
        old.setIsActive(courseSection.getIsActive() != null ? courseSection.getIsActive() : old.getIsActive());

        if (old.getCreatedAt() == null) {
            old.setCreatedAt(courseSection.getCreatedAt());
        }
        if (old.getCreatedBy() == null) {
            old.setCreatedBy(courseSection.getCreatedBy());
        }

        return repo.save(old);
    }

    // 5. Search course section
    public List<CourseSections> search(String keyword, String code, String academicYear, Integer maxStudents,
            Integer minStudents, String classType, String status) {
        return repo.findAll(
                buildSearchSpecification(keyword, code, academicYear, maxStudents, minStudents, classType, status));
    }

    // 6. Paginated search course section
    public Page<CourseSections> searchPaged(String keyword, String code, String academicYear, Integer maxStudents,
            Integer minStudents, String classType, String status, int page, int size) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.max(size, 1), Sort.by(Sort.Direction.ASC, "code"));
        return repo.findAll(
                buildSearchSpecification(keyword, code, academicYear, maxStudents, minStudents, classType, status),
                pageable);
    }

    // 7. Soft delete course section
    public void softDelete(UUID id) {
        CourseSections courseSection = getById(id);
        if (courseSection == null) {
            return;
        }

        courseSection.setDeletedAt(LocalDateTime.now());
        courseSection.setIsActive(false);
        courseSection.setUpdatedAt(LocalDateTime.now());
        repo.save(courseSection);
    }

    // Helper method to check if a string has text
    private boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private Specification<CourseSections> buildSearchSpecification(String keyword, String code, String academicYear,
            Integer maxStudents, Integer minStudents, String classType, String status) {
        Specification<CourseSections> spec = (root, query, cb) -> cb.isNull(root.get("deletedAt"));

        if (hasText(keyword)) {
            String pattern = "%" + keyword.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.or(
                    cb.like(cb.lower(root.get("code")), pattern),
                    cb.like(cb.lower(root.get("academicYear")), pattern),
                    cb.like(cb.lower(root.get("classType")), pattern),
                    cb.like(cb.lower(root.get("status")), pattern),
                    cb.like(cb.lower(root.get("note")), pattern)));
        }

        if (hasText(code)) {
            String pattern = "%" + code.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("code")), pattern));
        }

        if (hasText(academicYear)) {
            String pattern = "%" + academicYear.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("academicYear")), pattern));
        }

        if (maxStudents != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("maxStudents"), maxStudents));
        }

        if (minStudents != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("minStudents"), minStudents));
        }

        if (hasText(classType)) {
            String pattern = "%" + classType.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("classType")), pattern));
        }

        if (hasText(status)) {
            String pattern = "%" + status.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("status")), pattern));
        }

        return spec;
    }

}
