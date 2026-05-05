package com.example.demo.semesters.service;

import com.example.demo.semesters.model.entity.Semesters;
import com.example.demo.semesters.repository.SemestersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SemestersService {

    private final SemestersRepository repo;

    public SemestersService(SemestersRepository repo) {
        this.repo = repo;
    }

    public List<Semesters> getAll() {
        return repo.findAll();
    }

    public Semesters create(Semesters s) {
        s.setId(UUID.randomUUID().toString());
        return repo.save(s);
    }

    public Semesters update(String id, Semesters s) {
        s.setId(id);
        return repo.save(s);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}