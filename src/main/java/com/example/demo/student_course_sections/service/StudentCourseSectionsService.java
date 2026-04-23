package com.example.demo.student_course_sections.service;

import com.example.demo.student_course_sections.model.entity.StudentCourseSections;
import com.example.demo.student_course_sections.repository.StudentCourseSectionsRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseSectionsService {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final StudentCourseSectionsRepository repo;

    public StudentCourseSectionsService(StudentCourseSectionsRepository repo) {
        this.repo = repo;
    }

    // 1. Get all student course sections
    public List<StudentCourseSections> getAll() {
        return repo.findAllWithDetails();
    }

    // 2. Get a student course section by ID
    public StudentCourseSections getById(UUID id) {
        return repo.findByIdAndDeletedAtIsNullWithDetails(id).orElse(null);
    }

    // 3. Update a student course section
    public StudentCourseSections update(UUID id, StudentCourseSections studentCourseSection) {
        StudentCourseSections old = getById(id);
        if (old == null) {
            return null;
        }

        old.setStudentId(studentCourseSection.getStudentId());
        old.setCourseSectionId(studentCourseSection.getCourseSectionId());
        old.setStatus(studentCourseSection.getStatus());
        old.setRegisteredAt(studentCourseSection.getRegisteredAt());
        old.setNote(studentCourseSection.getNote());
        old.setUpdatedAt(LocalDateTime.now());
        old.setUpdatedBy(studentCourseSection.getUpdatedBy());
        old.setIsActive(
                studentCourseSection.getIsActive() != null ? studentCourseSection.getIsActive() : old.getIsActive());

        if (old.getCreatedAt() == null) {
            old.setCreatedAt(studentCourseSection.getCreatedAt());
        }
        if (old.getCreatedBy() == null) {
            old.setCreatedBy(studentCourseSection.getCreatedBy());
        }

        return repo.save(old);
    }

    // 4. Soft delete a student course section
    public void softDelete(UUID id) {
        StudentCourseSections studentCourseSection = getById(id);
        if (studentCourseSection == null) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        studentCourseSection.setDeletedAt(now);
        studentCourseSection.setIsActive(false);
        studentCourseSection.setUpdatedAt(now);
        repo.save(studentCourseSection);
    }

    // 5. Search student course sections
    public List<StudentCourseSections> search(UUID studentId, UUID courseSectionId, String keyword, String status,
            Boolean isActive, String studentCode, String studentName, String gender, String courseSectionCode) {
        return repo.findAll(buildSearchSpecification(studentId, courseSectionId, keyword, status, isActive, studentCode,
                studentName, gender, courseSectionCode));
    }

    // 6. Paginated student course sections
    public Page<StudentCourseSections> searchPaged(UUID studentId, UUID courseSectionId, String keyword, String status,
            Boolean isActive, String studentCode, String studentName, String gender, String courseSectionCode, int page,
            int size) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.max(size, 1),
                Sort.by(Sort.Direction.DESC, "createdAt"));
        return repo.findAll(buildSearchSpecification(studentId, courseSectionId, keyword, status, isActive, studentCode,
                studentName, gender, courseSectionCode), pageable);
    }

    // Helper method to check if a string has text
    private boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Helper method to build search specification
    private Specification<StudentCourseSections> buildSearchSpecification(UUID studentId, UUID courseSectionId,
            String keyword, String status, Boolean isActive, String studentCode, String studentName, String gender,
            String courseSectionCode) {
        Specification<StudentCourseSections> spec = (root, query, cb) -> cb.isNull(root.get("deletedAt"));

        if (studentId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("studentId"), studentId));
        }

        if (courseSectionId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("courseSectionId"), courseSectionId));
        }

        if (hasText(keyword)) {
            String pattern = "%" + keyword.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.or(
                    cb.like(cb.lower(root.get("status")), pattern),
                    cb.like(cb.lower(root.get("note")), pattern),
                    cb.like(cb.lower(root.join("student").get("code")), pattern),
                    cb.like(cb.lower(root.join("student").get("fullname")), pattern),
                    cb.like(cb.lower(root.join("student").get("gender")), pattern),
                    cb.like(cb.lower(root.join("courseSection").get("code")), pattern),
                    cb.like(cb.lower(root.get("studentId").as(String.class)), pattern),
                    cb.like(cb.lower(root.get("courseSectionId").as(String.class)), pattern)));
        }

        if (hasText(status)) {
            String pattern = "%" + status.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("status")), pattern));
        }

        if (isActive != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("isActive"), isActive));
        }

        if (hasText(studentCode)) {
            String pattern = "%" + studentCode.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.join("student").get("code")), pattern));
        }

        if (hasText(studentName)) {
            String pattern = "%" + studentName.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.join("student").get("fullname")), pattern));
        }

        if (hasText(gender)) {
            String pattern = "%" + gender.trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.join("student").get("gender")), pattern));
        }

        if (hasText(courseSectionCode)) {
            String pattern = "%" + courseSectionCode.trim().toLowerCase() + "%";
            spec = spec.and(
                    (root, query, cb) -> cb.like(cb.lower(root.join("courseSection").get("code")), pattern));
        }

        return spec;
    }

    // 7. Download student course sections as .xlsx
    public byte[] download() throws IOException {
        List<StudentCourseSections> items = repo.findAllWithDetails();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Student Course Sections");

            String[] headers = {
                    "STT",
                    "Ma sinh vien",
                    "Ten sinh vien",
                    "Gioi tinh",
                    "Lop hoc phan",
                    "Trang thai",
                    "Ngay dang ky",
                    "Ghi chu",
                    "Hoat dong"
            };

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            for (int i = 0; i < items.size(); i++) {
                StudentCourseSections item = items.get(i);
                Row row = sheet.createRow(i + 1);

                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(getStudentCode(item));
                row.createCell(2).setCellValue(getStudentName(item));
                row.createCell(3).setCellValue(getStudentGender(item));
                row.createCell(4).setCellValue(getCourseSectionCode(item));
                row.createCell(5).setCellValue(defaultString(item.getStatus()));
                row.createCell(6).setCellValue(formatDateTime(item.getRegisteredAt()));
                row.createCell(7).setCellValue(defaultString(item.getNote()));
                row.createCell(8).setCellValue(Boolean.TRUE.equals(item.getIsActive()) ? "Active" : "Inactive");
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

    // Helper methods to safely get related entity properties
    private String getStudentCode(StudentCourseSections item) {
        if (item.getStudent() != null && item.getStudent().getCode() != null) {
            return item.getStudent().getCode();
        }
        return item.getStudentId() != null ? item.getStudentId().toString() : "";
    }

    private String getStudentName(StudentCourseSections item) {
        if (item.getStudent() != null && item.getStudent().getFullname() != null) {
            return item.getStudent().getFullname();
        }
        return "";
    }

    private String getStudentGender(StudentCourseSections item) {
        if (item.getStudent() != null && item.getStudent().getGender() != null) {
            return item.getStudent().getGender();
        }
        return "";
    }

    private String getCourseSectionCode(StudentCourseSections item) {
        if (item.getCourseSection() != null && item.getCourseSection().getCode() != null) {
            return item.getCourseSection().getCode();
        }
        return item.getCourseSectionId() != null ? item.getCourseSectionId().toString() : "";
    }

    private String formatDateTime(LocalDateTime value) {
        return value != null ? value.format(DATE_TIME_FORMATTER) : "";
    }

    private String defaultString(String value) {
        return value != null ? value : "";
    }
}
