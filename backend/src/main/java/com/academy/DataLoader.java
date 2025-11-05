package com.academy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.academy.model.Course;
import com.academy.model.User;
import com.academy.repository.CourseRepository;
import com.academy.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(CourseRepository courseRepo, UserRepository userRepo) {
        return args -> {
            if (courseRepo.count() == 0) {
                courseRepo.save(new Course("English - Basic", "English", "BASIC"));
                courseRepo.save(new Course("French - Basic", "French", "BASIC"));
                courseRepo.save(new Course("Italian - Basic", "Italian", "BASIC"));
                courseRepo.save(new Course("Excel - Basic", "Excel", "BASIC"));
                courseRepo.save(new Course("Excel - Intermediate", "Excel", "INTERMEDIATE"));
                courseRepo.save(new Course("AI for Students", "IA", "BASIC"));
                courseRepo.save(new Course("Python - Basic", "Python", "BASIC"));
            }

            if (userRepo.count() == 0) {
                userRepo.save(new User("admin","admin123","ADMIN"));
                userRepo.save(new User("student","student123","STUDENT"));
            }
        };
    }
}
