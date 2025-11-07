package com.academy.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.academy.BackendApplication;

@CucumberContextConfiguration
@SpringBootTest(
    classes = BackendApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
public class CucumberSpringConfiguration {
    // Esta clase solo sirve para configuración, no necesita contenido
}