package com.oscar.pozuelo.dto;


import java.time.LocalDateTime;

public class EnrollmentDto {

    private Long id;
    private String studentName;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public String getStudentName() { return studentName; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
