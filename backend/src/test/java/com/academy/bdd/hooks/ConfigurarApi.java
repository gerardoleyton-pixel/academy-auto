package com.academy.bdd.hooks;

import io.cucumber.java.Before;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.RestAssured;

public class ConfigurarApi {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("base.uri", "http://localhost:" + port);
        RestAssured.baseURI = baseUrl;
    }
}
