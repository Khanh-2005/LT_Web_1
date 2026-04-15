package com.example.demo.course_sections.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "course_sections")
public class CourseSections {
    @Id
    @GeneratedValue
    @UuidGenerator // Hibernate 6+
    @Column(columnDefinition = "UNIQUEIDENTIFIER", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "code", length = 100)
    private String code;

    @Column(name = "course_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID courseId;

    @Column(name = "semester_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID semesterId;

    @Column(name = "academic_year", length = 20)
    private String academicYear;

    @Column(name = "employee_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID employeeId;

    @Column(name = "room_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID roomId;

    @Column(name = "building_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID buildingId;

    @Column(name = "max_students")
    private Integer maxStudents;

    @Column(name = "min_students")
    private Integer minStudents;

    @Column(name = "class_type", length = 50)
    private String classType;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "registration_start")
    private LocalDateTime registrationStart;

    @Column(name = "registration_end")
    private LocalDateTime registrationEnd;

    @Column(name = "note", length = 255)
    private String note;

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

    public CourseSections() {
    }

    public CourseSections(String code,
            UUID courseId,
            UUID semesterId,
            String academicYear,
            UUID employeeId,
            UUID roomId,
            UUID buildingId,
            Integer maxStudents,
            Integer minStudents,
            String classType,
            String status,
            LocalDateTime registrationStart,
            LocalDateTime registrationEnd,
            String note,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            UUID createdBy,
            UUID updatedBy,
            LocalDateTime deletedAt,
            UUID deletedBy,
            Boolean isActive) {
        this.code = code;
        this.courseId = courseId;
        this.semesterId = semesterId;
        this.academicYear = academicYear;
        this.employeeId = employeeId;
        this.roomId = roomId;
        this.buildingId = buildingId;
        this.maxStudents = maxStudents;
        this.minStudents = minStudents;
        this.classType = classType;
        this.status = status;
        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
        this.note = note;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedAt = deletedAt;
        this.deletedBy = deletedBy;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public UUID getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(UUID semesterId) {
        this.semesterId = semesterId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public UUID getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(UUID buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }

    public Integer getMinStudents() {
        return minStudents;
    }

    public void setMinStudents(Integer minStudents) {
        this.minStudents = minStudents;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRegistrationStart() {
        return registrationStart;
    }

    public void setRegistrationStart(LocalDateTime registrationStart) {
        this.registrationStart = registrationStart;
    }

    public LocalDateTime getRegistrationEnd() {
        return registrationEnd;
    }

    public void setRegistrationEnd(LocalDateTime registrationEnd) {
        this.registrationEnd = registrationEnd;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
