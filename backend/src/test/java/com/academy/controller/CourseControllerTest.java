package com.academy.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.academy.model.Course;
import com.academy.repository.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CourseRepository repo;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setup() { repo.deleteAll(); }

    @Test
    void testCRUD() throws Exception {
        // Create
        Course c = new Course("English Basics","English","BASIC");
        String payload = objectMapper.writeValueAsString(c);

        String location = mockMvc.perform(post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
            .andExpect(status().isCreated())
            .andReturn().getResponse().getHeader("Location");

        // Get all
        mockMvc.perform(get("/api/courses")).andExpect(status().isOk()).andExpect(jsonPath("$[0].title").value("English Basics"));

        // Update
        Course updated = new Course("English 101","English","BASIC");
        String updatePayload = objectMapper.writeValueAsString(updated);

        // determine id from repo
        Long id = repo.findAll().get(0).getId();

        mockMvc.perform(put("/api/courses/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatePayload))
                .andExpect(status().isOk()).andExpect(jsonPath("$.title").value("English 101"));

        // Delete
        mockMvc.perform(delete("/api/courses/"+id)).andExpect(status().isNoContent());

        mockMvc.perform(get("/api/courses/"+id)).andExpect(status().isNotFound());
    }
}
