package com.academy.hooks.legacy;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.springframework.boot.test.web.server.LocalServerPort;

/**
 * Cucumber hook to configure REST base URI for API tests. Runs after Spring context is available
 */
public class ConfigurarApiLegacy {

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        // Legacy hook - not used. Keeps previous RestAssured configuration in case needed.
        RestAssured.baseURI = System.getProperty("base.uri", "http://localhost:" + port);
    }
}
