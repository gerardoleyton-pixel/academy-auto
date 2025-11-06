package com.academy.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EliminarUsuarioApi implements Task {

    private final Long id;

    public EliminarUsuarioApi(Long id) {
        this.id = id;
    }

    public static EliminarUsuarioApi con(Long id) {
        return instrumented(EliminarUsuarioApi.class, id);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .when()
                .delete("/api/users/" + id);
    }
}
