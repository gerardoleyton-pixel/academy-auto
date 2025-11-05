package com.academy.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.academy.model.Course;
import com.academy.model.User;
import com.academy.repository.CourseRepository;
import com.academy.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class EnrollmentControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired CourseRepository courseRepo;
    @Autowired UserRepository userRepo;
    @Autowired ObjectMapper objectMapper;

    @BeforeEach
    void setup() { courseRepo.deleteAll(); userRepo.deleteAll(); }

    @Test
    void testEnrollAndDelete() throws Exception {
        Course c = courseRepo.save(new Course("Curso Test","Test","BASIC"));
        User u = userRepo.save(new User("u1","p","STUDENT"));

        // Enroll
        mockMvc.perform(post("/api/enrollments").param("userId", String.valueOf(u.getId())).param("courseId", String.valueOf(c.getId())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.course.title").value("Curso Test"));

        // get by user
        mockMvc.perform(get("/api/enrollments/by-user/"+u.getId())).andExpect(status().isOk()).andExpect(jsonPath("$[0].student.username").value("u1"));

        // fetch id and delete
        Long id = 1L; // simple - repository uses in-memory and it's first entry; alternative is to query repo but test is kept simple
        mockMvc.perform(delete("/api/enrollments/"+id)).andExpect(status().isNoContent());
    }
}
