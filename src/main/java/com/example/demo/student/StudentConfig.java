package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JULY;
import static java.time.Month.OCTOBER;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student even = new Student(
                    "Even",
                    "Even@gmail.com",
                    LocalDate.of(1989, JULY, 23)
            );

            Student enda = new Student(
                    "Enda",
                    "Enda@gmail.com",
                    LocalDate.of(1988, OCTOBER, 2)
            );

            repository.saveAll(
                    List.of(even, enda)
            );
        };
    }
}
