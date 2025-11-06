package com.academy.tasks;

import com.academy.models.Usuario;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CrearUsuarioApi implements Task {

    private final Usuario usuario;

    public CrearUsuarioApi(Usuario usuario) {
        this.usuario = usuario;
    }

    public static CrearUsuarioApi con(Usuario usuario) {
        return instrumented(CrearUsuarioApi.class, usuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .contentType("application/json")
                .body(usuario)
                .when()
                .post("/api/users");
    }
}
