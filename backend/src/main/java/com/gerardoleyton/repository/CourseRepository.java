package com.gerardoleyton.repository;

import com.gerardoleyton.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
