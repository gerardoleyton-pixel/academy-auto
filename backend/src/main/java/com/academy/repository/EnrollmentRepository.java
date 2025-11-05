package com.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
