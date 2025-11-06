package com.academy.bdd.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.*;

public class MatriculaSteps {
    private Response response;
    private JSONObject requestBody;
    private String token;

    @Given("un estudiante autenticado")
    public void unEstudianteAutenticado() {
        // Primero hacemos login para obtener el token
        JSONObject loginBody = new JSONObject();
        loginBody.put("username", "student@test.com");
        loginBody.put("password", "password123");

        Response loginResponse = SerenityRest.given()
            .contentType("application/json")
            .body(loginBody.toString())
            .when()
            .post("http://localhost:8080/api/auth/login");

        token = loginResponse.jsonPath().getString("token");
        assertNotNull(token);
    }

    @When("selecciona un curso disponible para matricularse")
    public void seleccionaUnCursoDisponibleParaMatricularse() {
        requestBody = new JSONObject();
        requestBody.put("courseId", 1);

        response = SerenityRest.given()
            .contentType("application/json")
            .header("Authorization", "Bearer " + token)
            .body(requestBody.toString())
            .when()
            .post("http://localhost:8080/api/courses/enroll");
    }

    @Then("se confirma la matrícula exitosa")
    public void seConfirmaLaMatriculaExitosa() {
        assertEquals(200, response.getStatusCode());
        assertNotNull(response.jsonPath().getString("message"));
        assertTrue(response.jsonPath().getString("message").contains("matriculado exitosamente"));
    }
}