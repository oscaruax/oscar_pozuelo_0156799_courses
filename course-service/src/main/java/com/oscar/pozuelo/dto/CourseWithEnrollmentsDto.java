package com.oscar.pozuelo.dto;

import java.time.LocalDate;
import java.util.List;

public class CourseWithEnrollmentsDto {

    private Long id;
    private String title;
    private String teacher;
    private LocalDate startDate;
    private int capacity;
    private List<EnrollmentDto> enrollments;

    public CourseWithEnrollmentsDto(Long id, String title, String teacher,
                                    LocalDate startDate, int capacity,
                                    List<EnrollmentDto> enrollments) {
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.startDate = startDate;
        this.capacity = capacity;
        this.enrollments = enrollments;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getTeacher() { return teacher; }
    public LocalDate getStartDate() { return startDate; }
    public int getCapacity() { return capacity; }
    public List<EnrollmentDto> getEnrollments() { return enrollments; }
}
