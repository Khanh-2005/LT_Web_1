package com.example.demo.academic_years.service;

import com.example.demo.academic_years.model.entity.AcademicYears;
import com.example.demo.academic_years.repository.AcademicYearsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AcademicYearsService {

    @Autowired
    private AcademicYearsRepository repo;

    public List<AcademicYears> getAll() {
        return repo.findAll();
    }

    public AcademicYears getById(String id) {
        return repo.findById(id).orElse(null);
    }

    public AcademicYears create(AcademicYears body) {
        body.setId(UUID.randomUUID().toString());
        body.setCreatedAt(new Date());
        body.setUpdatedAt(new Date());
        return repo.save(body);
    }

    public AcademicYears update(String id, AcademicYears body) {
        AcademicYears existing = repo.findById(id).orElse(null);
        if (existing == null)
            return null;

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