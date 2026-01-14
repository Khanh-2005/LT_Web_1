package com.example.studentmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.studentmanager.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Tìm đúng 1 sinh viên theo tên (không phân biệt hoa thường)
    Optional<Student> findByNameIgnoreCase(String name);
}
