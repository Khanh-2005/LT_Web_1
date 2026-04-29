package com.example.demo.lecturer_course_classes.controller;

import com.example.demo.lecturer_course_classes.model.entity.LecturerCourseClasses;
import com.example.demo.lecturer_course_classes.service.LecturerCourseClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lecturer-course-classes")
@CrossOrigin
public class LecturerCourseClassController {

    private final LecturerCourseClassService service;

    public LecturerCourseClassController(LecturerCourseClassService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public List<LecturerCourseClasses> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<LecturerCourseClasses> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<LecturerCourseClasses> create(@RequestBody LecturerCourseClasses entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<LecturerCourseClasses> update(
            @PathVariable UUID id,
            @RequestBody LecturerCourseClasses entity) {
        return ResponseEntity.ok(service.update(id, entity));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}