package com.academy.tasks;

import com.academy.models.Usuario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static com.academy.utils.Constantes.API_URL;

public class CrearUsuario implements Task {
    
    private final Usuario usuario;

    public CrearUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public static CrearUsuario conDatos(Usuario usuario) {
        return instrumented(CrearUsuario.class, usuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .contentType("application/json")
                .body(usuario)
                .post(API_URL + "/api/users");
    }
}