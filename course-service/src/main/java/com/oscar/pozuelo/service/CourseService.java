package com.oscar.pozuelo.service;

import com.oscar.pozuelo.dto.*;
import com.oscar.pozuelo.model.Course;
import com.oscar.pozuelo.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.*;
import java.util.Comparator;

@Service
public class CourseService {

    private final CourseRepository repository;
    private final WebClient webClient;

    public CourseService(CourseRepository repository, WebClient webClient) {
        this.repository = repository;
        this.webClient = webClient;
    }

    public List<Course> findAll() {
        return repository.findAll();
    }

    public Course save(Course course) {
        return repository.save(course);
    }

    public Course update(Long id, Course updated) {
        Course course = repository.findById(id).orElseThrow();
        course.setTitle(updated.getTitle());
        course.setTeacher(updated.getTeacher());
        course.setStartDate(updated.getStartDate());
        course.setCapacity(updated.getCapacity());
        return repository.save(course);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public CourseWithEnrollmentsDto findByIdWithEnrollments(Long id) {
        Course course = repository.findById(id).orElseThrow();

        List<EnrollmentDto> enrollments = webClient
                .get()
                .uri("http://localhost:8082/enrollments/course/{id}", id)
                .retrieve()
                .bodyToFlux(EnrollmentDto.class)
                .collectList()
                .block();

        return new CourseWithEnrollmentsDto(
                course.getId(),
                course.getTitle(),
                course.getTeacher(),
                course.getStartDate(),
                course.getCapacity(),
                enrollments
        );
    }

    public CourseReportDto generateReport() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Callable<Long> totalCourses = () -> repository.count();

        Callable<Long> availableCourses = () ->
        repository.findAll().stream()
                .filter(course -> {
                    Long enrollmentsCount = webClient
                            .get()
                            .uri("http://localhost:8082/enrollments/course/{id}", course.getId())
                            .retrieve()
                            .bodyToFlux(EnrollmentDto.class)
                            .count()
                            .block();

                    return enrollmentsCount < course.getCapacity();
                })
                .count();


        Callable<String> maxCapacityCourse = () ->
                repository.findAll().stream()
                        .max(Comparator.comparingInt(Course::getCapacity))
                        .map(Course::getTitle)
                        .orElse("N/A");

        Future<Long> total = executor.submit(totalCourses);
        Future<Long> available = executor.submit(availableCourses);
        Future<String> maxCourse = executor.submit(maxCapacityCourse);

        CourseReportDto report = new CourseReportDto(
                total.get(),
                available.get(),
                maxCourse.get()
        );

        executor.shutdown();
        return report;
    }
}
