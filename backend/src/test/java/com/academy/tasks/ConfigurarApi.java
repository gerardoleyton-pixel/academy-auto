package com.academy.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static com.academy.utils.Constantes.API_URL;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConfigurarApi implements Task {

    public static ConfigurarApi enLaUrl() {
        return instrumented(ConfigurarApi.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.can(CallAnApi.at(API_URL));
    }
}