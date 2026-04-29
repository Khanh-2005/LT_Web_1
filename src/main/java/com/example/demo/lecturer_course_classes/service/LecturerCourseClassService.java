package com.example.demo.lecturer_course_classes.service;

import com.example.demo.lecturer_course_classes.model.entity.LecturerCourseClasses;
import com.example.demo.lecturer_course_classes.repository.LecturerCourseClassRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LecturerCourseClassService {

    private final LecturerCourseClassRepository repository;

    public LecturerCourseClassService(LecturerCourseClassRepository repository) {
        this.repository = repository;
    }

    // GET ALL
    public List<LecturerCourseClasses> getAll() {
        return repository.findAllActive();
    }

    // GET BY ID
    public LecturerCourseClasses getById(UUID id) {
        return repository.findActiveById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bản ghi với id: " + id));
    }

    // CREATE
    public LecturerCourseClasses create(LecturerCourseClasses entity) {
        // Kiểm tra trùng
        if (repository.existsActiveByEmployeeAndCourseClass(
                entity.getEmployeeId(), entity.getCourseClassId())) {
            throw new RuntimeException("Giảng viên đã được phân công lớp học phần này rồi");
        }
        entity.setId(null); // để @UuidGenerator tự tạo
        entity.setIsActive(true);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setDeletedAt(null);
        return repository.save(entity);
    }

    // UPDATE
    public LecturerCourseClasses update(UUID id, LecturerCourseClasses request) {
        LecturerCourseClasses existing = getById(id);
        existing.setEmployeeId(request.getEmployeeId());
        existing.setCourseClassId(request.getCourseClassId());
        existing.setRole(request.getRole());
        existing.setUpdatedAt(LocalDateTime.now());
        return repository.save(existing);
    }

    // DELETE (soft delete)
    public void delete(UUID id) {
        LecturerCourseClasses existing = getById(id);
        existing.setIsActive(false);
        existing.setDeletedAt(LocalDateTime.now());
        repository.save(existing);
    }
}