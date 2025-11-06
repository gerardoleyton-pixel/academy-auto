package com.gerardoleyton.integration;

import com.gerardoleyton.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createAndListCourses() {
        String base = "http://localhost:" + port + "/api/courses";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{\"title\":\"IT Integration Course\",\"description\":\"Test course\"}";
        ResponseEntity<Course> create = restTemplate.postForEntity(base, new HttpEntity<>(body, headers), Course.class);
        assertThat(create.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(create.getBody()).isNotNull();

        ResponseEntity<Course[]> list = restTemplate.getForEntity(base, Course[].class);
        assertThat(list.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(list.getBody()).isNotEmpty();
    }
}
