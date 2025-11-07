package com.academy.runners;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/gestion_usuario.feature")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,timeline:build/test-results/timeline,summary")
@ConfigurationParameter(key = "cucumber.object-factory", value = "io.cucumber.spring.SpringFactory")
@ConfigurationParameter(key = "cucumber.glue", value = "com.academy.bdd")
public class TestRunnerCreacionUsuarioApi {
}
