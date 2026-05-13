
// @RestController
// @RequestMapping("/api/employees")
// public class EmployeesController {

// private final EmployeesRepository repo;

// public EmployeesController(EmployeesRepository repo) {
// this.repo = repo;
// }

// // 1. Get all employees
// @GetMapping
// public List<Employees> list() {
// return repo.findAll();
// }

// // 2. Get employee by id
// @GetMapping("/{id}")
// public Employees getById(@PathVariable UUID id) {
// return repo.findById(id).orElse(null);
// }

// // 3. Create new employee
// @PostMapping
// public Employees create(@RequestBody Employees employees) {
// return repo.save(employees);
// }

// // 4. Update employee
// @PutMapping("/{id}")
// public Employees update(@PathVariable UUID id, @RequestBody Employees
// employees) {
// employees.setId(id);
// return repo.save(employees);
// }

// // 5. Soft delete employee
// @DeleteMapping("/{id}")
// public void softdelete(@PathVariable UUID id) {
// repo.deleteById(id);
// }

// // 6. Search employees
// @GetMapping("/search")
// public List<Employees> search(@RequestParam String keyword) {
// // Implement search logic here, e.g., search by name or email
// return repo.findAll(); // Placeholder, replace with actual search
// implementation
// }

// // 7. Pagination
// }

package com.example.demo.employees.controller;

import com.example.demo.employees.model.entity.Employees;
import com.example.demo.employees.repository.EmployeesRepository;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

//import org.springframework.data.domain.Page;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// import com.example.demo.employees.repository.EmployeesRepository;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeesController {

    private final EmployeesRepository employeesRepository;

    public EmployeesController(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    // 1. Get all employees
    @GetMapping
    public List<Employees> getAll() {
        return employeesRepository.findAll().stream()
                .filter(employee -> employee.getDeletedAt() == null)
                .toList();
    }

    // 2. Get employee by id
    @GetMapping("/{id}")
    public Employees getById(@PathVariable UUID id) {
        return employeesRepository.findById(id)
                .filter(employee -> employee.getDeletedAt() == null)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // 3. Create new employee
    @PostMapping
    public Employees create(@RequestBody Employees employees) {
        employees.setId(UUID.randomUUID());
        employees.setIsActive(true);
        employees.setDeletedAt(null);
        LocalDateTime now = LocalDateTime.now();
        employees.setCreatedAt(now);
        employees.setUpdatedAt(now);
        return employeesRepository.save(employees);
    }

    // 4. Update employee
    @PutMapping("/{id}")
    public Employees update(@PathVariable UUID id, @RequestBody Employees employees) {
        Employees existingEmployee = getById(id);
        existingEmployee.setCode(employees.getCode());
        existingEmployee.setFullName(employees.getFullName());
        existingEmployee.setGender(employees.getGender());
        existingEmployee.setEmail(employees.getEmail());
        existingEmployee.setAcademicTitle(employees.getAcademicTitle());
        existingEmployee.setAcademicDegree(employees.getAcademicDegree());
        existingEmployee.setContractType(employees.getContractType());
        existingEmployee.setIsActive(employees.getIsActive() == null ? true : employees.getIsActive());
        existingEmployee.setPositionId(employees.getPositionId());
        existingEmployee.setDepartmentId(employees.getDepartmentId());
        existingEmployee.setUpdatedAt(LocalDateTime.now());
        return employeesRepository.save(existingEmployee);
    }

    // 5. Soft delete employee
    @DeleteMapping("/{id}")
    public void softdelete(@PathVariable UUID id) {
        Employees employee = getById(id);
        employee.setIsActive(false);
        employee.setDeletedAt(LocalDateTime.now());
        employeesRepository.save(employee);
    }

    // 6. Search employees
    @GetMapping("/search")
    public List<Employees> search(@RequestParam String keyword) {
        String normalizedKeyword = keyword == null ? "" : keyword.toLowerCase();
        return getAll().stream()
                .filter(employee -> contains(employee.getFullName(), normalizedKeyword)
                        || contains(employee.getCode(), normalizedKeyword)
                        || contains(employee.getEmail(), normalizedKeyword))
                .toList();
    }

    // 7. Pagination
    @GetMapping("/paged")
    public List<Employees> getPage(@RequestParam int page, @RequestParam int size) {
        return getAll().stream()
                .skip((long) page * size)
                .limit(size)
                .toList();
    }

    private boolean contains(String value, String keyword) {
        return value != null && value.toLowerCase().contains(keyword);
    }
}
