package com.pruebas.ui.tasks;

import com.pruebas.ui.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginWithCredentials implements Task {

    private final String email;
    private final String password;

    public LoginWithCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static LoginWithCredentials using(String email, String password) {
        return instrumented(LoginWithCredentials.class, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(LoginPage.INPUT_EMAIL, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(email).into(LoginPage.INPUT_EMAIL),
                Enter.theValue(password).into(LoginPage.INPUT_PASSWORD),
                Click.on(LoginPage.BUTTON_SUBMIT)
        );
    }
}