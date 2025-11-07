package com.academy.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static com.academy.utils.Constantes.API_URL;

public class EliminarUsuario implements Task {
    
    private final Integer idUsuario;

    public EliminarUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public static EliminarUsuario conId(Integer idUsuario) {
        return instrumented(EliminarUsuario.class, idUsuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.delete(API_URL + "/api/users/" + idUsuario);
    }
}