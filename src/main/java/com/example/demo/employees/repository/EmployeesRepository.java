// EmployeeRepository
package com.example.demo.employees.repository;

import com.example.demo.employees.model.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeesRepository extends JpaRepository<Employees, UUID> {

    List<Employees> findByFullNameContainingAndPositionId(String fullName, UUID positionId);

}