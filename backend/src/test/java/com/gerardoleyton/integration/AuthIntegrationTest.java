package com.gerardoleyton.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void registerAndLogin() {
        String base = "http://localhost:" + port + "/api/auth";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{\"username\":\"ituser\",\"password\":\"itpass\",\"email\":\"it@local\"}";
        HttpEntity<String> req = new HttpEntity<>(body, headers);
        ResponseEntity<Map> regResp = restTemplate.postForEntity(base + "/register", req, Map.class);
        assertThat(regResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(regResp.getBody()).containsKeys("id", "username");

        String loginBody = "{\"username\":\"ituser\",\"password\":\"itpass\"}";
        ResponseEntity<Map> loginResp = restTemplate.postForEntity(base + "/login", new HttpEntity<>(loginBody, headers), Map.class);
        assertThat(loginResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResp.getBody()).containsKey("token");
    }
}
