package com.oscar.pozuelo.dto;

public class CourseReportDto {

    private long totalCourses;
    private long availableCourses;
    private String maxCapacityCourse;

    public CourseReportDto(long totalCourses, long availableCourses, String maxCapacityCourse) {
        this.totalCourses = totalCourses;
        this.availableCourses = availableCourses;
        this.maxCapacityCourse = maxCapacityCourse;
    }

    public long getTotalCourses() { return totalCourses; }
    public long getAvailableCourses() { return availableCourses; }
    public String getMaxCapacityCourse() { return maxCapacityCourse; }
}

