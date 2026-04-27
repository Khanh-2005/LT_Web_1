package com.example.demo.academic_years.controller;

import com.example.demo.academic_years.model.entity.AcademicYear;
import com.example.demo.academic_years.service.AcademicYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academic-years")
@CrossOrigin(origins = "*")
public class AcademicYearApiController {

    @Autowired
    private AcademicYearService service;

    @GetMapping
    public List<AcademicYear> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AcademicYear getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public AcademicYear create(@RequestBody AcademicYear body) {
        return service.create(body);
    }

    @PutMapping("/{id}")
    public AcademicYear update(@PathVariable String id, @RequestBody AcademicYear body) {
        return service.update(id, body);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}