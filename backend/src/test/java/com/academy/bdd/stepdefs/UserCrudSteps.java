package com.academy.bdd.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserCrudSteps {
    @LocalServerPort
    private Integer port = 0;
    private String username;
    private String password;
    private String role;
    private Response response;
    private Long userId;

    @Given("que quiero crear un usuario con username {string} password {string} y role {string}")
    public void que_quiero_crear_un_usuario_con_username_password_y_role(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @When("envío la petición de creación")
    public void envio_la_peticion_de_creacion() {
        String requestBody = String.format("{\"username\":\"%s\",\"password\":\"%s\",\"role\":\"%s\"}", 
            username, password, role);
        
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(requestBody)
            .when()
            .post("http://localhost:" + port + "/api/users");
    }

    @Then("recibo código {int}")
    public void recibo_codigo(Integer expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @And("guardo el id de la última respuesta")
    public void guardo_el_id_de_la_ultima_respuesta() {
        userId = response.jsonPath().getLong("id");
    }

    @When("envío la petición de actualización con username {string} password {string} y role {string}")
    public void envio_la_peticion_de_actualizacion_con_username_password_y_role(String newUsername, String newPassword, String newRole) {
        String requestBody = String.format("{\"username\":\"%s\",\"password\":\"%s\",\"role\":\"%s\"}", 
            newUsername, newPassword, newRole);
        
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(requestBody)
            .when()
            .put("http://localhost:" + port + "/api/users/" + userId);
    }

    @When("envío la petición de eliminación")
    public void envio_la_peticion_de_eliminacion() {
        response = given()
            .when()
            .delete("http://localhost:" + port + "/api/users/" + userId);
    }
}