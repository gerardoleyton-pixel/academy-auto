package com.academy.runners;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import com.academy.Application;

@CucumberContextConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestRunnerCreacionUsuarioApi {
    // Runner class to bootstrap Spring context for Cucumber API tests
}
