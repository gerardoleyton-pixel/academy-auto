package com.academy.bdd.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {
    private Response response;
    private JSONObject requestBody;
    private String token;

    @Given("un usuario registrado accede al formulario de login")
    public void unUsuarioRegistradoAccedeAlFormularioDeLogin() {
        requestBody = new JSONObject();
    }

    @When("ingresa sus credenciales correctas")
    public void ingresaSusCredencialesCorrectas() {
        requestBody.put("username", "test@example.com");
        requestBody.put("password", "password123");

        response = SerenityRest.given()
            .contentType("application/json")
            .body(requestBody.toString())
            .when()
            .post("http://localhost:8080/api/auth/login");
    }

    @Then("inicia sesión correctamente")
    public void iniciaSesionCorrectamente() {
        assertEquals(200, response.getStatusCode());
        token = response.jsonPath().getString("token");
        assertNotNull(token);
    }
}