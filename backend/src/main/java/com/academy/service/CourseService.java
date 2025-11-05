package com.academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.academy.model.Course;
import com.academy.repository.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) { this.repository = repository; }

    public List<Course> findAll() { return repository.findAll(); }
    public Optional<Course> findById(Long id) { return repository.findById(id); }
    public Course save(Course c) { return repository.save(c); }
    public void deleteById(Long id) { repository.deleteById(id); }
}
