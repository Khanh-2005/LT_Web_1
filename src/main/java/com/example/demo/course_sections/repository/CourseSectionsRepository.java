package com.example.demo.course_sections.repository;

import com.example.demo.course_sections.model.entity.CourseSections;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSectionsRepository
        extends JpaRepository<CourseSections, UUID> {

    List<CourseSections> findByCodeContainingIgnoreCase(String code);
}