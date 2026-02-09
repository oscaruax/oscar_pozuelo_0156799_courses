package com.oscar.pozuelo.model;

import java.time.LocalDateTime;

public class Enrollment {

    private Long id;
    private Long courseId;
    private String studentName;
    private LocalDateTime createdAt;

    public Enrollment() {}

    public Enrollment(Long id, Long courseId, String studentName, LocalDateTime createdAt) {
        this.id = id;
        this.courseId = courseId;
        this.studentName = studentName;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public Long getCourseId() { return courseId; }
    public String getStudentName() { return studentName; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
