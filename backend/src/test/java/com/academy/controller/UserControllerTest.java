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

import com.academy.model.User;
import com.academy.repository.UserRepository;
import com.academy.repository.EnrollmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired UserRepository userRepo;
    @Autowired EnrollmentRepository enrollmentRepo;
    @Autowired ObjectMapper objectMapper;

    @BeforeEach
    void setup() { 
        enrollmentRepo.deleteAll();
        userRepo.deleteAll(); 
    }

    @Test
    void testCRUD() throws Exception {
        User u = new User("testuser","p","STUDENT");
        String payload = objectMapper.writeValueAsString(u);

        // create
        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(payload)).andExpect(status().isCreated()).andExpect(jsonPath("$.username").value("testuser"));

        // get all
        mockMvc.perform(get("/api/users")).andExpect(status().isOk()).andExpect(jsonPath("$[0].username").value("testuser"));

        // update
        Long id = userRepo.findAll().get(0).getId();
        u.setUsername("updated");
        String up = objectMapper.writeValueAsString(u);
        mockMvc.perform(put("/api/users/"+id).contentType(MediaType.APPLICATION_JSON).content(up)).andExpect(status().isOk()).andExpect(jsonPath("$.username").value("updated"));

        // delete
        mockMvc.perform(delete("/api/users/"+id)).andExpect(status().isNoContent());
    }
}
