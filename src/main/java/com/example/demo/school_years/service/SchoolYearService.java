package com.example.demo.school_years.service;

import com.example.demo.school_years.model.entity.SchoolYear;
import com.example.demo.school_years.repository.SchoolYearRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SchoolYearService {

    private final SchoolYearRepository repo;

    public SchoolYearService(SchoolYearRepository repo) {
        this.repo = repo;
    }

    public List<SchoolYear> getAll() {
        return repo.findAll();
    }

    public SchoolYear create(SchoolYear s) {
        s.setId(UUID.randomUUID().toString());
        return repo.save(s);
    }

    public SchoolYear update(String id, SchoolYear s) {
        s.setId(id);
        return repo.save(s);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}