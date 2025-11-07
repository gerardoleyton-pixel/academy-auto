package com.academy.bdd;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, 
    value = "pretty, json:target/cucumber-report/cucumber.json, html:target/cucumber-report/cucumber.html")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.academy.bdd.stepdefs,com.academy.bdd.config")
@ConfigurationParameter(key = Constants.OBJECT_FACTORY_PROPERTY_NAME, value = "io.cucumber.spring.SpringFactory")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "")
public class CucumberTestRunner {
}