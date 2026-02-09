package com.oscar.pozuelo.batch;


import com.oscar.pozuelo.dto.CsvCourse;
import com.oscar.pozuelo.model.Course;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class CourseItemProcessor implements ItemProcessor<CsvCourse, Course> {

    @Override
    public Course process(CsvCourse item) {
        return new Course(
                item.getTitle(),
                item.getTeacher(),
                item.getCapacity(),
                LocalDate.parse(item.getStartDate())
        );
    }
}

