package com.academy.bdd;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.academy.bdd"},
    plugin = {"pretty", "json:target/cucumber-report/cucumber.json"}
)
public class SerenityCucumberRunner {
}
