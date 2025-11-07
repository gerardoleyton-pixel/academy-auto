package com.academy.stepdefinitions;

import com.academy.models.Usuario;
import com.academy.tasks.CrearUsuario;
import com.academy.tasks.ActualizarUsuario;
import com.academy.tasks.EliminarUsuario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.rest.SerenityRest;
import io.cucumber.java.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.academy.hooks.TestContext;

public class GestionarUsuarioStepDefinitions {
    
    private Usuario usuario;
    private Integer idUsuario;

    @Before
    public void prepararEscenario() {
        // TestContext se inicializa estáticamente; la habilidad CallAnApi se configura en ApiHook
    }

    @Given("que quiero crear un usuario con username {string} password {string} y role {string}")
    public void queQuieroCrearUnUsuario(String username, String password, String role) {
        usuario = new Usuario(username, password, role);
    }

    @When("envío la petición de creación")
    public void envíoLaPeticiónDeCreación() {
        TestContext.ACTOR.attemptsTo(
            CrearUsuario.conDatos(usuario)
        );
    }

    @io.cucumber.java.en.And("guardo el id de la última respuesta")
    public void guardoElIdDeLaUltimaRespuesta() {
        Long id = SerenityRest.lastResponse().jsonPath().getLong("id");
        if (id != null) {
            this.idUsuario = id.intValue();
        }
    }

    @Given("que quiero actualizar el usuario con id {int}")
    public void queQuieroActualizarElUsuario(Integer id) {
        this.idUsuario = id;
    }

    @When("envío la petición de actualización con username {string} password {string} y role {string}")
    public void envíoLaPeticiónDeActualización(String username, String password, String role) {
        usuario = new Usuario(username, password, role);
        TestContext.ACTOR.attemptsTo(
            ActualizarUsuario.conDatos(usuario, idUsuario)
        );
    }

    @Given("que quiero eliminar el usuario con id {int}")
    public void queQuieroEliminarElUsuario(Integer id) {
        this.idUsuario = id;
    }

    @When("envío la petición de eliminación")
    public void envíoLaPeticiónDeEliminación() {
        TestContext.ACTOR.attemptsTo(
            EliminarUsuario.conId(idUsuario)
        );
    }

    @Then("recibo código {int}")
    public void reciboStatus(int expectedStatus) {
        assertEquals(expectedStatus, SerenityRest.lastResponse().statusCode());
    }
}
