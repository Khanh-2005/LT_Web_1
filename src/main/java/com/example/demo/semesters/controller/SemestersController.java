package com.example.demo.semesters.controller;

import com.example.demo.semesters.model.entity.Semesters;
import com.example.demo.semesters.service.SemestersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semesters")
@CrossOrigin
public class SemestersController {

    private final SemestersService service;

    public SemestersController(SemestersService service) {
        this.service = service;
    }

    @GetMapping
    public List<Semesters> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Semesters create(@RequestBody Semesters s) {
        return service.create(s);
    }

    @PutMapping("/{id}")
    public Semesters update(@PathVariable String id, @RequestBody Semesters s) {
        return service.update(id, s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}