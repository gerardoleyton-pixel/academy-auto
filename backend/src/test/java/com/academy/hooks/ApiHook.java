package com.academy.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import com.academy.tasks.ConfigurarApi;
import static com.academy.utils.Constantes.API_URL;
import org.springframework.beans.factory.annotation.Autowired;
import com.academy.repository.UserRepository;

public class ApiHook {

    @Before
    public void prepararEscenario() {
        // Registrar la habilidad de la API en el actor global
        TestContext.ACTOR.can(CallAnApi.at(API_URL));
        // también aseguramos que la tarea de configuración quede explícita
        TestContext.ACTOR.attemptsTo(ConfigurarApi.enLaUrl());
    }
}