package com.pruebas.ui.tasks;

import com.pruebas.ui.userinterfaces.ContactListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Logout implements Task {

    public static Logout fromApplication() {
        return new Logout();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ContactListPage.BUTTON_LOGOUT, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(ContactListPage.BUTTON_LOGOUT)
        );
    }
}