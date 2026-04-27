package com.example.demo.academic_years.service;

import com.example.demo.academic_years.model.entity.AcademicYear;
import com.example.demo.academic_years.repository.AcademicYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AcademicYearService {

    @Autowired
    private AcademicYearRepository repo;

    public List<AcademicYear> getAll() {
        return repo.findAll();
    }

    public AcademicYear getById(String id) {
        return repo.findById(id).orElse(null);
    }

    public AcademicYear create(AcademicYear body) {
        body.setId(UUID.randomUUID().toString());
        body.setCreatedAt(new Date());
        body.setUpdatedAt(new Date());
        return repo.save(body);
    }

    public AcademicYear update(String id, AcademicYear body) {
        AcademicYear existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setCode(body.getCode());
        existing.setName(body.getName());
        existing.setYear(body.getYear());
        existing.setDescription(body.getDescription());
        existing.setStartDate(body.getStartDate());
        existing.setEndDate(body.getEndDate());
        existing.setIsActive(body.getIsActive());
        existing.setUpdatedAt(new Date());

        return repo.save(existing);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}