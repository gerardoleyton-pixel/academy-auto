package com.gerardoleyton.service;

import com.gerardoleyton.model.Course;
import com.gerardoleyton.model.User;
import com.gerardoleyton.repository.CourseRepository;
import com.gerardoleyton.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course create(Course course) { return courseRepository.save(course); }
    public List<Course> list() { return courseRepository.findAll(); }
    public Optional<Course> findById(Long id) { return courseRepository.findById(id); }

    public Optional<Course> enrollStudent(Long courseId, Long userId) {
        Optional<Course> oc = courseRepository.findById(courseId);
        Optional<User> ou = userRepository.findById(userId);
        if (oc.isPresent() && ou.isPresent()) {
            Course c = oc.get();
            c.getStudents().add(ou.get());
            return Optional.of(courseRepository.save(c));
        }
        return Optional.empty();
    }
}
