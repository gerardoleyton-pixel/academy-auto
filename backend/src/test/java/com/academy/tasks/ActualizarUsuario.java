package com.academy.tasks;

import com.academy.models.Usuario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static com.academy.utils.Constantes.API_URL;

public class ActualizarUsuario implements Task {
    
    private final Usuario usuario;
    private final Integer idUsuario;

    public ActualizarUsuario(Usuario usuario, Integer idUsuario) {
        this.usuario = usuario;
        this.idUsuario = idUsuario;
    }

    public static ActualizarUsuario conDatos(Usuario usuario, Integer idUsuario) {
        return instrumented(ActualizarUsuario.class, usuario, idUsuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .contentType("application/json")
                .body(usuario)
                .put(API_URL + "/api/users/" + idUsuario);
    }
}