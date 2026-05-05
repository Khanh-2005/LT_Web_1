package com.example.demo.students.repository;

import com.example.demo.students.model.entity.Students;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentsRepository extends JpaRepository<Students, UUID>, JpaSpecificationExecutor<Students> {
    List<Students> findByDeletedAtIsNull();

    Optional<Students> findByIdAndDeletedAtIsNull(UUID id);

    boolean existsByIdAndDeletedAtIsNull(UUID id);

    List<Students> findByFullnameContainingIgnoreCase(String fullname);
}
