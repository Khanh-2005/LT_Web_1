package com.example.demo.courses.controller;

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

import com.example.demo.courses.model.entity.Courses;
import com.example.demo.courses.service.CoursesService;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin
public class CoursesController {

    private final CoursesService service;

    public CoursesController(CoursesService service) {
        this.service = service;
    }

    // 1. GET ALL /api/courses
    @GetMapping
    public List<Courses> getAll(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String courseType,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        return service.getAll(keyword, courseType, status, page, size);
    }

    // 2. GET BY ID/api/courses/{id}
    @GetMapping("/{id}")
    public Courses getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    // 3. POST /api/courses
    @PostMapping
    public Courses create(@RequestBody Courses course) {
        return service.create(course);
    }

    // 4. PUT /api/courses/{id}
    @PutMapping("/{id}")
    public Courses update(@PathVariable UUID id, @RequestBody Courses course) {
        return service.update(id, course);
    }

    // 5. DELETE /api/courses/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.softDelete(id);
    }

    // 6. SEARCH ----- GET
    // /api/courses/search?keyword=...&courseType=...&status=...&page=...&size=...
    @GetMapping("/search")
    public List<Courses> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String courseType,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        return service.search(keyword, courseType, status, page, size);
    }

}