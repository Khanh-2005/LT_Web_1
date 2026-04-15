package com.example.demo.course_sections.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.course_sections.model.entity.CourseSections;
import com.example.demo.course_sections.repository.CourseSectionsRepository;
import java.util.UUID;

@Service
public class CourseSectionsService {
    private final CourseSectionsRepository repo;

    public CourseSectionsService(CourseSectionsRepository repo) {
        this.repo = repo;
    }

    public List<CourseSections> getAll() {
        return repo.findAll();
    }

    public CourseSections getById(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public CourseSections create(CourseSections courseSection) {
        return repo.save(courseSection);
    }

    public CourseSections update(UUID id, CourseSections courseSection) {
        if (!repo.existsById(id)) {
            return null;
        }
        courseSection.setId(id);
        return repo.save(courseSection);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }

    public List<CourseSections> search(String code) {
        return repo.findByCodeContainingIgnoreCase(code);
    }

}