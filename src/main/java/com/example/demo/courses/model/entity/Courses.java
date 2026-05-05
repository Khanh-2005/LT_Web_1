package com.example.demo.courses.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import java.math.BigDecimal;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "UNIQUEIDENTIFIER", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "code", length = 20, nullable = false, unique = true)
    private String courseCode;

    @Column(name = "name", length = 255, nullable = false)
    private String courseName;

    @Column(name = "name_en", length = 255)
    private String courseNameEn;

    @Column(precision = 5, scale = 1)
    private BigDecimal credits;

    @Column(name = "course_type", length = 20)
    private String courseType;

    @Column(name = "theory_hours", precision = 5, scale = 1)
    private BigDecimal theoryHours;

    @Column(name = "practice_hours", precision = 5, scale = 1)
    private BigDecimal practiceHours;

    @Column(name = "self_study_hours", precision = 5, scale = 1)
    private BigDecimal selfStudyHours;

    @Column(name = "internship_credits", precision = 5, scale = 1)
    private BigDecimal internshipCredits;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;

    // Thành
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "created_by", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID createdBy;

    @Column(name = "updated_by", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID updatedBy;

    @Column(name = "deleted_by", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID deletedBy;

    // Constructors
    public Courses() {
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNameEn() {
        return courseNameEn;
    }

    public void setCourseNameEn(String courseNameEn) {
        this.courseNameEn = courseNameEn;
    }

    public BigDecimal getCredits() {
        return credits;
    }

    public void setCredits(BigDecimal credits) {
        this.credits = credits;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public BigDecimal getTheoryHours() {
        return theoryHours;
    }

    public void setTheoryHours(BigDecimal theoryHours) {
        this.theoryHours = theoryHours;
    }

    public BigDecimal getPracticeHours() {
        return practiceHours;
    }

    public void setPracticeHours(BigDecimal practiceHours) {
        this.practiceHours = practiceHours;
    }

    public BigDecimal getSelfStudyHours() {
        return selfStudyHours;
    }

    public void setSelfStudyHours(BigDecimal selfStudyHours) {
        this.selfStudyHours = selfStudyHours;
    }

    public BigDecimal getInternshipCredits() {
        return internshipCredits;
    }

    public void setInternshipCredits(BigDecimal internshipCredits) {
        this.internshipCredits = internshipCredits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
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

    public UUID getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(UUID deletedBy) {
        this.deletedBy = deletedBy;
    }

}