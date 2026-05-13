package com.example.demo.student_course_sections.model.dto;

import com.example.demo.student_course_sections.model.entity.StudentCourseSections;
import java.time.LocalDateTime;
import java.util.UUID;

public record StudentCourseSectionResponse(
        UUID id,
        UUID studentId,
        UUID courseSectionId,
        String status,
        LocalDateTime registeredAt,
        String note,
        LocalDateTime createdAt,
        UUID createdBy,
        UUID updatedBy,
        Boolean isActive,
        StudentSummary student,
        CourseSectionSummary courseSection) {

    public static StudentCourseSectionResponse fromEntity(StudentCourseSections item) {
        StudentSummary studentSummary = item.getStudent() == null
                ? null
                : new StudentSummary(
                        item.getStudent().getId(),
                        item.getStudent().getCode(),
                        item.getStudent().getFullname(),
                        item.getStudent().getGender());

        CourseSectionSummary courseSectionSummary = item.getCourseSection() == null
                ? null
                : new CourseSectionSummary(
                        item.getCourseSection().getId(),
                        item.getCourseSection().getCode(),
                        item.getCourseSection().getAcademicYear());

        return new StudentCourseSectionResponse(
                item.getId(),
                item.getStudentId(),
                item.getCourseSectionId(),
                item.getStatus(),
                item.getRegisteredAt(),
                item.getNote(),
                item.getCreatedAt(),
                item.getCreatedBy(),
                item.getUpdatedBy(),
                item.getIsActive(),
                studentSummary,
                courseSectionSummary);
    }

    public record StudentSummary(UUID id, String code, String fullname, String gender) {
    }

    public record CourseSectionSummary(UUID id, String code, String academicYear) {
    }
}
