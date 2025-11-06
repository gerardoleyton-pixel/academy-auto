package com.academy.tasks;

import com.academy.models.Usuario;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ActualizarUsuarioApi implements Task {

    private final Long id;
    private final Usuario usuario;

    public ActualizarUsuarioApi(Long id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public static ActualizarUsuarioApi con(Long id, Usuario usuario) {
        return instrumented(ActualizarUsuarioApi.class, id, usuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .contentType("application/json")
                .body(usuario)
                .when()
                .put("/api/users/" + id);
    }
}
