package com.example.demo.school_years.service;

import com.example.demo.school_years.model.entity.SchoolYears;
import com.example.demo.school_years.repository.SchoolYearsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SchoolYearsService {

    private final SchoolYearsRepository repo;

    public SchoolYearsService(SchoolYearsRepository repo) {
        this.repo = repo;
    }

    public List<SchoolYears> getAll() {
        return repo.findAll();
    }

    public SchoolYears create(SchoolYears s) {
        s.setId(UUID.randomUUID().toString());
        return repo.save(s);
    }

    public SchoolYears update(String id, SchoolYears s) {
        s.setId(id);
        return repo.save(s);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}