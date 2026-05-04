package com.example.demo.students.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.students.model.entity.Student;
import com.example.demo.students.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    // 1. GET ALL
    public List<Student> getAll() {
        return repo.findByDeletedAtIsNull();
    }

    // 2. GET BY ID
    public Student getById(UUID id) {
        return repo.findByIdAndDeletedAtIsNull(id).orElse(null);
    }

    // 3. CREATE
    public Student create(Student student) {
        LocalDateTime now = LocalDateTime.now();
        student.setId(null);
        student.setDeletedAt(null);
        student.setDeletedBy(null);
        student.setUpdatedAt(now);
        if (student.getCreatedAt() == null) {
            student.setCreatedAt(now);
        }
        if (student.getIsActive() == null) {
            student.setIsActive(true);
        }
        return repo.save(student);
    }

    // 4. UPDATE
    public Student update(UUID id, Student student) {
        Student old = getById(id);
        if (old == null) {
            return null;
        }

        old.setUser_id(student.getUser_id());
        old.setCode(student.getCode());
        old.setFullname(student.getFullname());
        old.setDate_of_birth(student.getDate_of_birth());
        old.setGender(student.getGender());
        old.setPersonal_identification_number(student.getPersonal_identification_number());
        old.setDate_of_issue(student.getDate_of_issue());
        old.setCard_place(student.getCard_place());
        old.setAddress(student.getAddress());
        old.setCurrent_address(student.getCurrent_address());
        old.setAcademic_year_year(student.getAcademic_year_year());
        old.setDepartment_id(student.getDepartment_id());
        old.setMajor_id(student.getMajor_id());
        old.setTraining_program_id(student.getTraining_program_id());
        old.setStatus(student.getStatus());
        old.setStudent_classe_id(student.getStudent_classe_id());
        old.setAdmission_year(student.getAdmission_year());
        old.setUpdatedAt(LocalDateTime.now());
        old.setUpdatedBy(student.getUpdatedBy());
        old.setIsActive(student.getIsActive() != null ? student.getIsActive() : old.getIsActive());

        if (old.getCreatedAt() == null) {
            old.setCreatedAt(student.getCreatedAt());
        }
        if (old.getCreatedBy() == null) {
            old.setCreatedBy(student.getCreatedBy());
        }

        return repo.save(old);
    }

    // 5. SOFT DELETE
    public void softDelete(UUID id) {
        Student student = getById(id);
        if (student == null) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        student.setDeletedAt(now);
        student.setUpdatedAt(now);
        student.setIsActive(false);
        repo.save(student);
    }

    // 6. SEARCH
    public List<Student> search(String keyword, String code, String fullname, String gender, String status,
            Boolean isActive) {
        return repo.findAll(buildSearchSpecification(keyword, code, fullname, gender, status, isActive));
    }

    // 7. PAGINATION
    public Page<Student> searchPaged(String keyword, String code, String fullname, String gender, String status,
            Boolean isActive, int page, int size) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.max(size, 1), Sort.by(Sort.Direction.ASC, "code"));
        return repo.findAll(buildSearchSpecification(keyword, code, fullname, gender, status, isActive), pageable);
    }

    // HELPER METHODS
    private boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // BUILD DYNAMIC SPECIFICATION FOR SEARCH
    private Specification<Student> buildSearchSpecification(String keyword, String code, String fullname, String gender,
            String status, Boolean isActive) {
        Specification<Student> spec = (root, query, cb) -> cb.isNull(root.get("deletedAt"));

        if (hasText(keyword)) {
            String pattern = "%" + keyword.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.or(
                    cb.like(cb.lower(root.get("code")), pattern),
                    cb.like(cb.lower(root.get("fullname")), pattern),
                    cb.like(cb.lower(root.get("gender")), pattern),
                    cb.like(cb.lower(root.get("personal_identification_number")), pattern),
                    cb.like(cb.lower(root.get("card_place")), pattern),
                    cb.like(cb.lower(root.get("address")), pattern),
                    cb.like(cb.lower(root.get("current_address")), pattern),
                    cb.like(cb.lower(root.get("status")), pattern)));
        }

        if (hasText(code)) {
            String pattern = "%" + code.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("code")), pattern));
        }

        if (hasText(fullname)) {
            String pattern = "%" + fullname.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("fullname")), pattern));
        }

        if (hasText(gender)) {
            String pattern = "%" + gender.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("gender")), pattern));
        }

        if (hasText(status)) {
            String pattern = "%" + status.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("status")), pattern));
        }

        if (isActive != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("isActive"), isActive));
        }

        return spec;
    }
}
