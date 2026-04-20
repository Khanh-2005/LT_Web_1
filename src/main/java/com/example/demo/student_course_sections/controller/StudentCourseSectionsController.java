package com.example.demo.student_course_sections.controller;

import com.example.demo.student_course_sections.model.entity.StudentCourseSections;
import com.example.demo.student_course_sections.service.StudentCourseSectionsService;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
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

@RestController
@RequestMapping("/api/student_course_sections")
@CrossOrigin
public class StudentCourseSectionsController {

    private final StudentCourseSectionsService service;

    public StudentCourseSectionsController(StudentCourseSectionsService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentCourseSections> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public StudentCourseSections getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public StudentCourseSections create(@RequestBody StudentCourseSections studentCourseSection) {
        return service.create(studentCourseSection);
    }

    @PutMapping("/{id}")
    public StudentCourseSections update(@PathVariable UUID id,
            @RequestBody StudentCourseSections studentCourseSection) {
        return service.update(id, studentCourseSection);
    }

    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable UUID id) {
        service.softDelete(id);
    }

    @GetMapping("/search")
    public List<StudentCourseSections> search(
            @RequestParam(required = false) UUID studentId,
            @RequestParam(required = false) UUID courseSectionId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean isActive) {
        return service.search(studentId, courseSectionId, keyword, status, isActive);
    }

    @GetMapping("/paged")
    public Page<StudentCourseSections> searchPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) UUID studentId,
            @RequestParam(required = false) UUID courseSectionId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean isActive) {
        return service.searchPaged(studentId, courseSectionId, keyword, status, isActive, page, size);
    }
}