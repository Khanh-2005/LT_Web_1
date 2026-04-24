package com.example.demo.lecturer_course_classes.controller;

import com.example.demo.lecturer_course_classes.service.LecturerCourseClassService;
import com.example.demo.lecturer_course_classes.model.entity.LecturerCourseClasses;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecturer-course-classes")
@CrossOrigin
public class LecturerCourseClassController {

    private final LecturerCourseClassService service;

    public LecturerCourseClassController(LecturerCourseClassService service) {
        this.service = service;
    }

    @GetMapping
    public List<LecturerCourseClasses> getAll() {
        return service.getAll();
    }
}
