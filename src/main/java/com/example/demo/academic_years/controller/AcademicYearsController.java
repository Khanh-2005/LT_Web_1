package com.example.demo.academic_years.controller;

import com.example.demo.academic_years.model.entity.AcademicYears;
import com.example.demo.academic_years.service.AcademicYearsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academic-years")
@CrossOrigin(origins = "*")
public class AcademicYearsController {

    @Autowired
    private AcademicYearsService service;

    @GetMapping
    public List<AcademicYears> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AcademicYears getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public AcademicYears create(@RequestBody AcademicYears body) {
        return service.create(body);
    }

    @PutMapping("/{id}")
    public AcademicYears update(@PathVariable String id, @RequestBody AcademicYears body) {
        return service.update(id, body);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}