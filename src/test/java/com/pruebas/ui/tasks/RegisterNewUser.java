package com.pruebas.ui.tasks;

import com.pruebas.ui.userinterfaces.LoginPage;
import com.pruebas.ui.userinterfaces.RegisterPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RegisterNewUser implements Task {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public RegisterNewUser(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public static RegisterNewUser withData(String firstName, String lastName, String email, String password) {
        return instrumented(RegisterNewUser.class, firstName, lastName, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LoginPage.BUTTON_SIGNUP),
                Enter.theValue(firstName).into(RegisterPage.INPUT_FIRST_NAME),
                Enter.theValue(lastName).into(RegisterPage.INPUT_LAST_NAME),
                Enter.theValue(email).into(RegisterPage.INPUT_EMAIL),
                Enter.theValue(password).into(RegisterPage.INPUT_PASSWORD),
                Click.on(RegisterPage.BUTTON_SUBMIT)
        );
    }
}