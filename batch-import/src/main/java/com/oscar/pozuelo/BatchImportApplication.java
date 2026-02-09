package com.oscar.pozuelo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BatchImportApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchImportApplication.class, args);
    }

    @Bean
    CommandLineRunner runJob(JobLauncher jobLauncher, Job importCoursesJob) {
        return args -> {
            jobLauncher.run(
                importCoursesJob,
                new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters()
            );
        };
    }
}
