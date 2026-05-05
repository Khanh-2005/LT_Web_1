package com.example.demo.courses.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.courses.model.entity.Courses;
import com.example.demo.courses.repository.CoursesRepository;

@Service
public class CoursesService {

    private final CoursesRepository repo;

    public CoursesService(CoursesRepository repo) {
        this.repo = repo;
    }

    // 1. GET ALL /api/courses
    public List<Courses> getAll(String keyword, String courseType, Integer status, Integer page, Integer size) {
        List<Courses> results = repo.findByDeletedAtIsNull();

        if (keyword != null && !keyword.isBlank()) {
            String lower = keyword.toLowerCase();
            results = results.stream()
                    .filter(c -> (c.getCourseCode() != null && c.getCourseCode().toLowerCase().contains(lower))
                            || (c.getCourseName() != null && c.getCourseName().toLowerCase().contains(lower))
                            || (c.getCourseNameEn() != null && c.getCourseNameEn().toLowerCase().contains(lower)))
                    .collect(Collectors.toList());
        }

        if (courseType != null && !courseType.isBlank()) {
            String lower = courseType.toLowerCase();
            results = results.stream()
                    .filter(c -> c.getCourseType() != null && c.getCourseType().toLowerCase().contains(lower))
                    .collect(Collectors.toList());
        }

        if (status != null) {
            Boolean active = status == 1;
            results = results.stream().filter(c -> active.equals(c.getIsActive())).collect(Collectors.toList());
        }

        if (page != null && size != null && page >= 0 && size > 0) {
            int from = page * size;
            int to = Math.min(results.size(), from + size);
            if (from >= results.size()) {
                return Collections.emptyList();
            }
            results = results.subList(from, to);
        }

        return results;
    }

    // 2. GET BY ID/api/courses/{id}
    public Courses getById(UUID id) {
        return repo.findById(id)
                .filter(c -> c.getDeletedAt() == null)
                .orElse(null);
    }

    // 3. POST /api/courses
    public Courses create(Courses input) {
        input.setId(null);
        input.setCreatedAt(LocalDateTime.now());
        input.setUpdatedAt(LocalDateTime.now());
        input.setDeletedAt(null);
        return repo.save(input);
    }

    // 4. PUT /api/courses/{id}
    public Courses update(UUID id, Courses course) {
        Courses existing = getById(id);
        if (existing == null) {
            return null;
        }
        if (course.getDescription() != null) {
            existing.setDescription(course.getDescription());
        }
        if (course.getInternshipCredits() != null) {
            existing.setInternshipCredits(course.getInternshipCredits());
        }

        existing.setCourseCode(course.getCourseCode());
        existing.setCourseName(course.getCourseName());
        existing.setCourseNameEn(course.getCourseNameEn());
        existing.setCredits(course.getCredits());
        existing.setCourseType(course.getCourseType());
        existing.setTheoryHours(course.getTheoryHours());
        existing.setPracticeHours(course.getPracticeHours());
        existing.setSelfStudyHours(course.getSelfStudyHours());
        existing.setInternshipCredits(course.getInternshipCredits());
        existing.setDescription(course.getDescription());
        existing.setIsActive(course.getIsActive());
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setUpdatedBy(course.getUpdatedBy());

        return repo.save(existing);
    }

    // 5. DELETE /api/courses/{id}
    public void softDelete(UUID id) {
        Courses existing = getById(id);
        if (existing == null) {
            return;
        }
        existing.setDeletedAt(LocalDateTime.now());
        existing.setUpdatedAt(LocalDateTime.now());
        repo.save(existing);
    }

    public List<Courses> search(String keyword, String courseType, Integer status, Integer page, Integer size) {
        return getAll(keyword, courseType, status, page, size);
    }
}
