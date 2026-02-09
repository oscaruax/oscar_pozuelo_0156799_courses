package com.oscar.pozuelo.processor;

import com.oscar.pozuelo.model.Course;
import com.oscar.pozuelo.dto.CsvCourse;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class CourseItemProcessor implements ItemProcessor<CsvCourse, Course> {

    @Override
    public Course process(CsvCourse item) {

        Course course = new Course();
        course.setTitle(item.getTitle());
        course.setTeacher(item.getTeacher());
        course.setCapacity(item.getCapacity());
        course.setStartDate(LocalDate.parse(item.getStartDate()));

        return course;
    }
}
