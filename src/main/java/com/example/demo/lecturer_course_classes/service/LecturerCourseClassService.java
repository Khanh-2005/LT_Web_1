package com.example.demo.lecturer_course_classes.service;

import com.example.demo.lecturer_course_classes.model.entity.LecturerCourseClasses;
import com.example.demo.lecturer_course_classes.repository.LecturerCourseClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerCourseClassService {

    private final LecturerCourseClassRepository repository;

    public LecturerCourseClassService(LecturerCourseClassRepository repository) {
        this.repository = repository;
    }

    public List<LecturerCourseClasses> getAll() {
        return repository.findAllActive();
    }
}