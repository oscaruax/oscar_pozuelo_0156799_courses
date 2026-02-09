package com.oscar.pozuelo.batch;

import com.oscar.pozuelo.model.Course;
import com.oscar.pozuelo.repository.CourseRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.Chunk;

public class CourseItemWriter implements ItemWriter<Course> {

    private final CourseRepository repository;

    public CourseItemWriter(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void write(Chunk<? extends Course> chunk) {
        repository.saveAll(chunk.getItems());
    }
}
