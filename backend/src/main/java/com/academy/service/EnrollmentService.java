package com.academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.academy.model.Course;
import com.academy.model.Enrollment;
import com.academy.model.User;
import com.academy.repository.CourseRepository;
import com.academy.repository.EnrollmentRepository;
import com.academy.repository.UserRepository;

@Service
public class EnrollmentService {
    private final EnrollmentRepository repo;
    private final CourseRepository courseRepo;
    private final UserRepository userRepo;

    public EnrollmentService(EnrollmentRepository repo, CourseRepository courseRepo, UserRepository userRepo) {
        this.repo = repo;
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
    }

    public List<Enrollment> findAll() { return repo.findAll(); }
    public Optional<Enrollment> findById(Long id) { return repo.findById(id); }

    public Enrollment enroll(Long userId, Long courseId) {
        User u = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Course c = courseRepo.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
        Enrollment e = new Enrollment(u, c);
        return repo.save(e);
    }

    public void deleteById(Long id) { repo.deleteById(id); }

    public List<Enrollment> findByUserId(Long userId) {
        // simple approach: fetch all and filter (could be repository method)
        return repo.findAll().stream().filter(en -> en.getStudent().getId().equals(userId)).toList();
    }
}
