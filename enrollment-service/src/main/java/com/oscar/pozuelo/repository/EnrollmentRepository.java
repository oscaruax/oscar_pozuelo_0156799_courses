package com.oscar.pozuelo.repository;

import com.oscar.pozuelo.model.Enrollment;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EnrollmentRepository {

    private final Map<Long, Enrollment> store = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Mono<Enrollment> save(Enrollment enrollment) {
        Long id = idGenerator.getAndIncrement();
        enrollment.setId(id);
        enrollment.setCreatedAt(LocalDateTime.now());
        store.put(id, enrollment);
        return Mono.just(enrollment);
    }

    public Flux<Enrollment> findAll() {
        return Flux.fromIterable(store.values());
    }

    public Flux<Enrollment> findByCourseId(Long courseId) {
        return Flux.fromIterable(store.values())
                .filter(e -> e.getCourseId().equals(courseId));
    }
}
