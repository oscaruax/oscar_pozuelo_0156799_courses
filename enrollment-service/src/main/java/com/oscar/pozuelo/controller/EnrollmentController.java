package com.oscar.pozuelo.controller;


import com.oscar.pozuelo.model.Enrollment;
import com.oscar.pozuelo.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<Enrollment> create(@RequestBody Enrollment enrollment) {
        return service.create(enrollment);
    }

    @GetMapping
    public Flux<Enrollment> getAll() {
        return service.findAll();
    }

    @GetMapping("/course/{courseId}")
    public Flux<Enrollment> getByCourse(@PathVariable Long courseId) {
        return service.findByCourseId(courseId);
    }
}
