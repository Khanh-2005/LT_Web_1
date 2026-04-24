package com.example.demo.employees.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.employees.model.entity.Employees;
import com.example.demo.employees.repository.EmployeesRepository;

@Service
public class EmployeesService {

    private final EmployeesRepository employeesRepo;

    public EmployeesService(EmployeesRepository employeesRepo) {
        this.employeesRepo = employeesRepo;
    }

    /**
     * Tạo employee mới
     */
    public Employees create(Employees employee) {
        return employeesRepo.save(employee);
    }

    /**
     * Lấy danh sách employees
     */
    public List<Employees> findAll() {
        return employeesRepo.findAll();
    }

    /**
     * Tìm employee theo id
     */
    public Employees findById(UUID id) {
        return employeesRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    /**
     * Xóa employee
     */
    public void delete(UUID id) {
        employeesRepo.deleteById(id);
    }
}