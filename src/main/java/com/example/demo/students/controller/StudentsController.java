package com.example.demo.students.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.students.model.entity.Students;
import com.example.demo.students.service.StudentsService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin // cho phép frontend gọi
public class StudentsController {

    private final StudentsService service;

    public StudentsController(StudentsService service) {
        this.service = service;
    }

    // 1. GET ALL
    @GetMapping
    public List<Students> getAll() {
        return service.getAll();
    }

    // 2. GET BY ID
    @GetMapping("/{id}")
    public Students getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    // 3. CREATE
    @PostMapping
    public Students create(@RequestBody Students student) {
        return service.create(student);
    }

    // 4. UPDATE
    @PutMapping("/{id}")
    public Students update(@PathVariable UUID id,
            @RequestBody Students student) {
        return service.update(id, student);
    }

    // 5. SOFT DELETE
    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable UUID id) {
        service.softDelete(id);
    }

    // 6. SEARCH
    @GetMapping("/search")
    public List<Students> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String fullname,
            @RequestParam(name = "full_name", required = false) String fullName,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean isActive) {
        String effectiveFullname = fullname != null ? fullname : fullName;
        return service.search(keyword, code, effectiveFullname, gender, status, isActive);
    }

    // 7. PAGINATION
    @GetMapping("/paged")
    public Page<Students> searchPaged(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String fullname,
            @RequestParam(name = "full_name", required = false) String fullName,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        String effectiveFullname = fullname != null ? fullname : fullName;
        return service.searchPaged(keyword, code, effectiveFullname, gender, status, isActive, page, size);
    }
}