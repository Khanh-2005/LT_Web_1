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

    @GetMapping
    public List<CourseSections> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CourseSections getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public CourseSections create(@RequestBody CourseSections courseSection) {
        return service.create(courseSection);
    }

    @PutMapping("/{id}")
    public CourseSections update(@PathVariable UUID id, @RequestBody CourseSections courseSection) {
        return service.update(id, courseSection);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<CourseSections> search(@RequestParam String fullname) {
        return service.search(fullname);
    }

}