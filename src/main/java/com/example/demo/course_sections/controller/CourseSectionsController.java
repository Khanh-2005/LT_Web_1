package com.example.demo.course_sections.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.course_sections.model.entity.CourseSections;
import com.example.demo.course_sections.service.CourseSectionsService;

@RestController
@RequestMapping("/api/course-sections")
@CrossOrigin // cho phép frontend gọi
public class CourseSectionsController {

    private final CourseSectionsService service;

    public CourseSectionsController(CourseSectionsService service) {
        this.service = service;
    }

    // 1. Get all course sections
    @GetMapping
    public List<CourseSections> getAll() {
        return service.getAll();
    }

    // 2. Get course section by ID
    @GetMapping("/{id}")
    public CourseSections getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    // 3. Create new course section
    @PostMapping
    public CourseSections create(@RequestBody CourseSections courseSection) {
        return service.create(courseSection);
    }

    // 4. Update existing course section
    @PutMapping("/{id}")
    public CourseSections update(@PathVariable UUID id, @RequestBody CourseSections courseSection) {
        return service.update(id, courseSection);
    }

    // 5. Soft delete course section
    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable UUID id) {
        service.softDelete(id);
    }

    // 6. Search course section
    @GetMapping("/search")
    public List<CourseSections> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String academicYear,
            @RequestParam(required = false) Integer maxStudents,
            @RequestParam(required = false) Integer minStudents,
            @RequestParam(required = false) String classType,
            @RequestParam(required = false) String status) {
        return service.search(keyword, code, academicYear, maxStudents, minStudents, classType, status);
    }

}
