package com.oscar.pozuelo.repository;


import com.oscar.pozuelo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
