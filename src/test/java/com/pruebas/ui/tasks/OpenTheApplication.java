package com.pruebas.ui.tasks;

import com.pruebas.ui.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenTheApplication implements Task {

    private static final String BASE_URL = "https://thinking-tester-contact-list.herokuapp.com";

    public static OpenTheApplication onLoginPage() {
        return new OpenTheApplication();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(BASE_URL),
                WaitUntil.the(LoginPage.INPUT_EMAIL, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}