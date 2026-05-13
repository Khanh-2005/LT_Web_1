package com.example.demo.student_course_sections.controller;

import com.example.demo.student_course_sections.model.dto.StudentCourseSectionResponse;
import com.example.demo.student_course_sections.model.entity.StudentCourseSections;
import com.example.demo.student_course_sections.service.StudentCourseSectionsService;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student_course_sections")
@CrossOrigin
public class StudentCourseSectionsController {

    private final StudentCourseSectionsService service;

    public StudentCourseSectionsController(StudentCourseSectionsService service) {
        this.service = service;
    }

    // 1. Get all student course sections
    @GetMapping
    public List<StudentCourseSectionResponse> getAll() {
        return service.getAll();
    }

    // 2. Get a student course section by ID
    @GetMapping("/{id}")
    public StudentCourseSectionResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    // 3. Update a student course section
    @PutMapping("/{id}")
    public StudentCourseSectionResponse update(@PathVariable UUID id,
            @RequestBody StudentCourseSections studentCourseSection) {
        return service.update(id, studentCourseSection);
    }

    // 4. Soft delete a student course section
    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable UUID id) {
        service.softDelete(id);
    }

    // 5. Search student course sections
    @GetMapping("/search")
    public List<StudentCourseSectionResponse> search(
            @RequestParam(required = false) UUID studentId,
            @RequestParam(required = false) UUID courseSectionId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) String studentCode,
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String courseSectionCode) {
        return service.search(studentId, courseSectionId, keyword, status, isActive, studentCode, studentName, gender,
                courseSectionCode);
    }

    // 6. Paginated student course sections
    @GetMapping("/paged")
    public Page<StudentCourseSectionResponse> searchPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) UUID studentId,
            @RequestParam(required = false) UUID courseSectionId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) String studentCode,
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String courseSectionCode) {
        return service.searchPaged(studentId, courseSectionId, keyword, status, isActive, studentCode, studentName,
                gender, courseSectionCode, page, size);
    }

    // 7. Download student course sections as .xlsx
    @GetMapping("/download")
    public ResponseEntity<byte[]> download() throws IOException {
        byte[] fileContent = service.download();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("student_course_sections.xlsx")
                .build());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileContent.length)
                .body(fileContent);
    }

}
