package com.example.demo.semesters.service;

import com.example.demo.semesters.model.entity.Semester;
import com.example.demo.semesters.repository.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SemesterService {

    private final SemesterRepository repo;

    public SemesterService(SemesterRepository repo) {
        this.repo = repo;
    }

    public List<Semester> getAll() {
        return repo.findAll();
    }

    public Semester create(Semester s) {
        s.setId(UUID.randomUUID().toString());
        return repo.save(s);
    }

    public Semester update(String id, Semester s) {
        s.setId(id);
        return repo.save(s);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}