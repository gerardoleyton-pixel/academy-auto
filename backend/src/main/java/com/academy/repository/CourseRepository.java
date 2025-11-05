package com.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
