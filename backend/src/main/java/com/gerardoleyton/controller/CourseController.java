package com.gerardoleyton.controller;

import com.gerardoleyton.model.Course;
import com.gerardoleyton.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.create(course));
    }

    @GetMapping
    public ResponseEntity<List<Course>> list() {
        return ResponseEntity.ok(courseService.list());
    }

    @PostMapping("/{courseId}/enroll/{userId}")
    public ResponseEntity<?> enroll(@PathVariable Long courseId, @PathVariable Long userId) {
        var oc = courseService.enrollStudent(courseId, userId);
        if (oc.isPresent()) {
            return ResponseEntity.ok(oc.get());
        }
        return ResponseEntity.badRequest().body("Course or user not found");
    }
}
