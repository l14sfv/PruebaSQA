package com.pruebas.runners.api;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = "com.pruebas.api.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class AutenticacionApiRunner {
}
