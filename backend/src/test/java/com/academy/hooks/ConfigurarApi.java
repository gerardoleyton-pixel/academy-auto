package com.academy.hooks;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import net.serenitybdd.rest.SerenityRest;
import org.springframework.beans.factory.annotation.Value;

public class ConfigurarApi {

    @Value("${local.server.port}")
    private int port;

    @Before
    public void configurarApi() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        
        // Configurar logging detallado para diagnóstico
        RestAssured.config = RestAssured.config()
            .logConfig(LogConfig.logConfig()
                .enableLoggingOfRequestAndResponseIfValidationFails());
                
        // Configurar Serenity para usar la misma configuración
        SerenityRest.setDefaultPort(port);
        SerenityRest.setDefaultBasePath("");
    }
}