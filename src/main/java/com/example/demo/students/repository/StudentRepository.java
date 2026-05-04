package com.example.demo.students.repository;

import com.example.demo.students.model.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student, UUID>, JpaSpecificationExecutor<Student> {
    List<Student> findByDeletedAtIsNull();

    Optional<Student> findByIdAndDeletedAtIsNull(UUID id);

    boolean existsByIdAndDeletedAtIsNull(UUID id);

    List<Student> findByFullnameContainingIgnoreCase(String fullname);
}
