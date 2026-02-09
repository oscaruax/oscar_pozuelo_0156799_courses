package com.oscar.pozuelo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String teacher;
    private LocalDate startDate;
    private int capacity;

    public Course() {}

    public Course(String title, String teacher, LocalDate startDate, int capacity) {
        this.title = title;
        this.teacher = teacher;
        this.startDate = startDate;
        this.capacity = capacity;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getTeacher() { return teacher; }
    public LocalDate getStartDate() { return startDate; }
    public int getCapacity() { return capacity; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setTeacher(String teacher) { this.teacher = teacher; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}
