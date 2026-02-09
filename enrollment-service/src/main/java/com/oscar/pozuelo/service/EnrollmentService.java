package com.oscar.pozuelo.service;

import com.oscar.pozuelo.model.Enrollment;
import com.oscar.pozuelo.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;

    public EnrollmentService(EnrollmentRepository repository) {
        this.repository = repository;
    }

    public Mono<Enrollment> create(Enrollment enrollment) {
        return repository.save(enrollment);
    }

    public Flux<Enrollment> findAll() {
        return repository.findAll();
    }

    public Flux<Enrollment> findByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }
}
