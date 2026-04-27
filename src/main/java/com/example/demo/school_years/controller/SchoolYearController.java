package com.example.demo.school_years.controller;

import com.example.demo.school_years.model.entity.SchoolYear;
import com.example.demo.school_years.service.SchoolYearService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school-years")
@CrossOrigin
public class SchoolYearController {

    private final SchoolYearService service;

    public SchoolYearController(SchoolYearService service) {
        this.service = service;
    }

    @GetMapping
    public List<SchoolYear> getAll() {
        return service.getAll();
    }

    @PostMapping
    public SchoolYear create(@RequestBody SchoolYear s) {
        return service.create(s);
    }

    @PutMapping("/{id}")
    public SchoolYear update(@PathVariable String id, @RequestBody SchoolYear s) {
        return service.update(id, s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}