package com.pruebas.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/ui/autenticacion_ui.feature",
        glue = {
                "com.pruebas.ui.stepdefinitions"
        },
        plugin = {
                "pretty"
        },
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true
)
public class AutenticacionUIRunner {
}