package com.oscar.pozuelo.config;

import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.transaction.PlatformTransactionManager;

import com.oscar.pozuelo.batch.CourseItemProcessor;
import com.oscar.pozuelo.batch.CourseItemWriter;
import com.oscar.pozuelo.dto.CsvCourse;
import com.oscar.pozuelo.model.Course;
import com.oscar.pozuelo.repository.CourseRepository;

@Configuration
public class BatchConfig {

	@Bean
	public FlatFileItemReader<CsvCourse> courseItemReader() {
	    FlatFileItemReader<CsvCourse> reader = new FlatFileItemReader<>();

	    reader.setResource(new ClassPathResource("courses.csv"));
	    reader.setLinesToSkip(1); // saltar cabecera

	    DefaultLineMapper<CsvCourse> lineMapper = new DefaultLineMapper<>();

	    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
	    tokenizer.setDelimiter(",");
	    tokenizer.setStrict(true);

	    // 🔴 ESTE ORDEN ES CRÍTICO
	    tokenizer.setNames("name", "teacher", "startDate", "capacity");

	    BeanWrapperFieldSetMapper<CsvCourse> fieldSetMapper =
	            new BeanWrapperFieldSetMapper<>();
	    fieldSetMapper.setTargetType(CsvCourse.class);

	    lineMapper.setLineTokenizer(tokenizer);
	    lineMapper.setFieldSetMapper(fieldSetMapper);

	    reader.setLineMapper(lineMapper);
	    return reader;
	}


    @Bean
    public CourseItemProcessor processor() {
        return new CourseItemProcessor();
    }

    @Bean
    public CourseItemWriter writer(CourseRepository repository) {
        return new CourseItemWriter(repository);
    }

    @Bean
    public Step importCoursesStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            FlatFileItemReader<CsvCourse> reader,
            CourseItemProcessor processor,
            CourseItemWriter writer
    ) {
        return new StepBuilder("importCoursesStep", jobRepository)
                .<CsvCourse, Course>chunk(5, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importCoursesJob(
            JobRepository jobRepository,
            Step importCoursesStep
    ) {
        return new JobBuilder("importCoursesJob", jobRepository)
                .start(importCoursesStep)
                .build();
    }
}
