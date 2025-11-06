package com.gerardoleyton.controller;

import com.gerardoleyton.model.Course;
import com.gerardoleyton.service.CourseService;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final CourseService courseService;

    public EnrollmentController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<?> enroll(@RequestParam Long userId, @RequestParam Long courseId) {
        var oc = courseService.enrollStudent(courseId, userId);
        if (oc.isPresent()) {
            return ResponseEntity.ok(Map.of("course", oc.get()));
        }
        return ResponseEntity.badRequest().body("Course or user not found");
    }
}
