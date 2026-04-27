package com.example.demo.semesters.controller;

import com.example.demo.semesters.model.entity.Semester;
import com.example.demo.semesters.service.SemesterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semesters")
@CrossOrigin
public class SemesterController {

    private final SemesterService service;

    public SemesterController(SemesterService service) {
        this.service = service;
    }

    @GetMapping
    public List<Semester> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Semester create(@RequestBody Semester s) {
        return service.create(s);
    }

    @PutMapping("/{id}")
    public Semester update(@PathVariable String id, @RequestBody Semester s) {
        return service.update(id, s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}