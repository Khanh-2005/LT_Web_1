package com.example.demo.lecturer_course_classes.model.entity;

import com.example.demo.course_sections.model.entity.CourseSections;
import com.example.demo.employees.model.entity.Employees;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "lecturer_course_classes")
public class LecturerCourseClasses {

    @Id
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    @UuidGenerator
    private UUID id;

    // ===== RELATION =====
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Employees employee;

    @Column(name = "employee_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID employeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_class_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CourseSections courseSection;

    @Column(name = "course_class_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID courseClassId;

    // ===== FIELD CHÍNH =====
    @Column(name = "role", length = 50)
    private String role;

    // ===== AUDIT =====
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID createdBy;

    @Column(name = "updated_by", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID updatedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID deletedBy;

    @Column(name = "is_active")
    private Boolean isActive;

    public LecturerCourseClasses() {
    }

    // Constructor dùng như RESPONSE (projection)
    public LecturerCourseClasses(
            UUID id,
            UUID employeeId,
            String employeeCode,
            String employeeFullName,
            String employeeEmail,
            String academicDegree,
            String academicTitle,
            UUID courseSectionId,
            String courseSectionCode,
            String courseSectionName,
            String classType,
            String status,
            String role,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            UUID createdBy,
            UUID updatedBy,
            LocalDateTime deletedAt,
            UUID deletedBy,
            Boolean isActive) {

        this.id = id;
        this.role = role;
        this.employeeId = employeeId;
        this.courseClassId = courseSectionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedAt = deletedAt;
        this.deletedBy = deletedBy;
        this.isActive = isActive;

        this.employee = new Employees();
        this.employee.setId(employeeId);
        this.employee.setCode(employeeCode);
        this.employee.setFullName(employeeFullName);
        this.employee.setEmail(employeeEmail);
        this.employee.setAcademicDegree(academicDegree);
        this.employee.setAcademicTitle(academicTitle);

        this.courseSection = new CourseSections();
        this.courseSection.setId(courseSectionId);
        this.courseSection.setCode(courseSectionCode);
        this.courseSection.setNote(courseSectionName);
        this.courseSection.setClassType(classType);
        this.courseSection.setStatus(status);
    }

    // ===== GETTER SETTER =====

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public CourseSections getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(CourseSections courseSection) {
        this.courseSection = courseSection;
    }

    public UUID getCourseClassId() {
        return courseClassId;
    }

    public void setCourseClassId(UUID courseClassId) {
        this.courseClassId = courseClassId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public UUID getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(UUID deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
