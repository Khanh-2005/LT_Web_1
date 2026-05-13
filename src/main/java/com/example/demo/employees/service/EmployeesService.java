package com.example.demo.employees.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.employees.model.entity.Employees;
import com.example.demo.employees.repository.EmployeesRepository;

@Service
public class EmployeesService {

    private final EmployeesRepository employeesRepo;

    public EmployeesService(EmployeesRepository employeesRepo) {
        this.employeesRepo = employeesRepo;
    }

    // 1. Get all employees
    public List<Employees> findAll() {
        return employeesRepo.findAll();
    }

    // 2. Get employee by id
    public Employees findById(UUID id) {
        return employeesRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // 3. Create new employee
    public Employees create(Employees employee) {
        return employeesRepo.save(employee);
    }

    // 4. Update employee
    public Employees update(UUID id, Employees employee) {
        Employees existingEmployee = findById(id);
        existingEmployee.setFullName(employee.getFullName());
        existingEmployee.setPositionId(employee.getPositionId());
        existingEmployee.setDepartmentId(employee.getDepartmentId());
        return employeesRepo.save(existingEmployee);
    }

    // 5. Soft delete employee
    public void softdelete(UUID id) {
        Employees employee = findById(id);

        employeesRepo.save(employee);
    }

    // 6. Search employees
    public List<Employees> search(String fullName, UUID positionId) {
        return employeesRepo.findByFullNameContainingAndPositionId(fullName, positionId);
    }

    // 7. Pagination
    public List<Employees> findAll(int page, int size) {
        return employeesRepo.findAll(PageRequest.of(page, size)).getContent();
    }
}