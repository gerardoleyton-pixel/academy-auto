package com.academy.bdd.stepdefs;

import com.academy.models.Usuario;
import com.academy.question.CodigoRespuesta;
import com.academy.tasks.CrearUsuarioApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import static org.assertj.core.api.Assertions.assertThat;

public class GestionarUsuarioStepDefinitions {

    private Actor actor = Actor.named("APIUser");
    private Usuario usuario;

    @Given("que quiero crear un usuario con nombre {string} y rol {string}")
    public void que_quiero_crear_un_usuario_con_nombre_y_rol(String username, String role) {
        this.usuario = new Usuario(username, "password123", role);
    }

    @When("envío la petición de creación")
    public void envio_la_peticion_de_creacion() {
        actor.attemptsTo(new CrearUsuarioApi(usuario));
    }

    @Then("recibo código {int}")
    public void recibo_codigo(Integer expected) {
        Integer status = actor.asksFor(CodigoRespuesta.current());
        assertThat(status).isEqualTo(expected);
    }
}
