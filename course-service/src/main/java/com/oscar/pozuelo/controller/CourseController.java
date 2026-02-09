package com.oscar.pozuelo.controller;

import com.oscar.pozuelo.dto.CourseReportDto;
import com.oscar.pozuelo.dto.CourseWithEnrollmentsDto;
import com.oscar.pozuelo.model.Course;
import com.oscar.pozuelo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return service.save(course);
    }

    @GetMapping("/{id}")
    public CourseWithEnrollmentsDto getById(@PathVariable Long id) {
        return service.findByIdWithEnrollments(id);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course course) {
        return service.update(id, course);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/informe")
    public CourseReportDto report() throws Exception {
        return service.generateReport();
    }
}
