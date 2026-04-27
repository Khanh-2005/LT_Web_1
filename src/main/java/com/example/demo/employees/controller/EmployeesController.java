// package com.example.demo.employees.controller;

// import java.util.List;
// import java.util.UUID;

// //import org.springframework.data.domain.Page;
// //import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PutMapping;
// //import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import io.swagger.v3.oas.annotations.parameters.RequestBody;

// import com.example.demo.employees.model.entity.Employees;
// import com.example.demo.employees.repository.EmployeesRepository;

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