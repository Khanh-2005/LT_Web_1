package com.example.demo.school_years.controller;

import com.example.demo.school_years.model.entity.SchoolYears;
import com.example.demo.school_years.service.SchoolYearsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school-years")
@CrossOrigin
public class SchoolYearsController {

    private final SchoolYearsService service;

    public SchoolYearsController(SchoolYearsService service) {
        this.service = service;
    }

    @GetMapping
    public List<SchoolYears> getAll() {
        return service.getAll();
    }

    @PostMapping
    public SchoolYears create(@RequestBody SchoolYears s) {
        return service.create(s);
    }

    @PutMapping("/{id}")
    public SchoolYears update(@PathVariable String id, @RequestBody SchoolYears s) {
        return service.update(id, s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}