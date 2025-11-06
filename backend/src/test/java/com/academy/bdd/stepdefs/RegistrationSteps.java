package com.academy.bdd.stepdefs;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationSteps {

    @LocalServerPort
    private int port;

    private String username;
    private String password;
    private Response response;
    private RequestSpecification request;
    private String token;

    @Given("I have a new user with username {string} and password {string}")
    public void iHaveANewUser(String username, String password) {
        this.username = username;
        this.password = password;
        RestAssured.baseURI = "http://localhost:" + port;
        request = RestAssured.given()
            .header("Content-Type", "application/json");
    }

    @When("I submit the registration request")
    public void iSubmitTheRegistrationRequest() {
        JSONObject requestParams = new JSONObject()
            .put("username", username)
            .put("password", password);

        response = request
            .body(requestParams.toString())
            .when()
            .post("/api/auth/register");
    }

    @Then("the registration should be successful")
    public void theRegistrationShouldBeSuccessful() {
        assertEquals(201, response.getStatusCode());
    }

    @And("I should receive a JWT token")
    public void iShouldReceiveAJWTToken() {
        // First login to get token
        JSONObject loginParams = new JSONObject()
            .put("username", username)
            .put("password", password);

        Response loginResponse = request
            .body(loginParams.toString())
            .when()
            .post("/api/auth/login");

        assertEquals(200, loginResponse.getStatusCode());
        assertNotNull(loginResponse.jsonPath().getString("token"));
        token = loginResponse.jsonPath().getString("token");
    }
}