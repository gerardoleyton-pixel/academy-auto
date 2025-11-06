package com.academy.bdd.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistroSteps {

    @LocalServerPort
    private int port;

    private String username;
    private String password;
    private Response response;
    private RequestSpecification request;
    private String token;

    private void setupRestAssured() {
        RestAssured.baseURI = "http://localhost:" + port;
        request = RestAssured.given()
            .header("Content-Type", "application/json");
    }

    @Given("el usuario accede al formulario de registro")
    public void userAccessesRegistrationForm() {
        this.username = "testuser" + System.currentTimeMillis();
        this.password = "Password123!";
        setupRestAssured();
    }

    @When("completa sus datos y envía el formulario")
    public void userFillsAndSubmitsForm() {
        JSONObject requestParams = new JSONObject()
            .put("username", username)
            .put("password", password)
            .put("email", username + "@test.com")
            .put("role", "ROLE_USER");

        response = request
            .body(requestParams.toString())
            .when()
            .post("/api/auth/register");
    }

    @Then("el sistema confirma el registro exitoso")
    public void systemConfirmsRegistration() {
        assertEquals(201, response.getStatusCode());
        assertNotNull(response.jsonPath().getString("token"));
    }

    // Login steps
    @Given("el usuario tiene credenciales válidas")
    public void userHasValidCredentials() {
        this.username = "testuser" + System.currentTimeMillis();
        this.password = "Password123!";
        setupRestAssured();
        
        // Register user first
        JSONObject registerParams = new JSONObject()
            .put("username", username)
            .put("password", password)
            .put("email", username + "@test.com")
            .put("role", "ROLE_USER");

        Response registerResponse = request
            .body(registerParams.toString())
            .when()
            .post("/api/auth/register");

        assertEquals(201, registerResponse.getStatusCode());
    }

    @When("intenta iniciar sesión")
    public void userAttemptsLogin() {
        JSONObject loginParams = new JSONObject()
            .put("username", username)
            .put("password", password);

        response = request
            .body(loginParams.toString())
            .when()
            .post("/api/auth/login");
    }

    @Then("recibe un token JWT")
    public void userReceivesJwtToken() {
        assertEquals(200, response.getStatusCode());
        token = response.jsonPath().getString("token");
        assertNotNull(token);
    }

    // Enrollment steps
    @Given("existe un curso disponible")
    public void courseIsAvailable() {
        setupRestAssured();
        
        // Create a course
        JSONObject courseParams = new JSONObject()
            .put("name", "Test Course")
            .put("description", "Test Description");

        Response courseResponse = request
            .header("Authorization", "Bearer " + token)
            .body(courseParams.toString())
            .when()
            .post("/api/courses");

        assertEquals(201, courseResponse.getStatusCode());
    }

    @Given("existe un usuario registrado")
    public void registeredUserExists() {
        // Already covered by userHasValidCredentials
        userHasValidCredentials();
        userAttemptsLogin();
        userReceivesJwtToken();
    }

    @When("el usuario solicita matricularse en el curso")
    public void userRequestsEnrollment() {
        response = request
            .header("Authorization", "Bearer " + token)
            .queryParam("courseId", "1")
            .queryParam("userId", "1")
            .when()
            .post("/api/enrollments");
    }

    @Then("la matricula se realiza con éxito")
    public void enrollmentIsSuccessful() {
        assertEquals(201, response.getStatusCode());
    }
}