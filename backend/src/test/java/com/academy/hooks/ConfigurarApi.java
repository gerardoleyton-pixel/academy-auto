package com.academy.bdd.hooks;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.springframework.boot.test.web.server.LocalServerPort;

/**
 * Cucumber hook to configure REST base URI for API tests. Runs after Spring context is available
 */
public class ConfigurarApi {

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        // Use the random port the embedded server started on during tests
        RestAssured.baseURI = System.getProperty("base.uri", "http://localhost:" + port);
    }
}
