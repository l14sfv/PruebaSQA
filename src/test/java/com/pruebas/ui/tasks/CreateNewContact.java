package com.pruebas.ui.tasks;

import com.pruebas.ui.userinterfaces.AddContactPage;
import com.pruebas.ui.userinterfaces.ContactListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CreateNewContact implements Task {

    private final String firstName;
    private final String lastName;
    private final String birthdate;

    public CreateNewContact(String firstName, String lastName, String birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    public static CreateNewContact withData(String firstName, String lastName, String birthdate) {
        return instrumented(CreateNewContact.class, firstName, lastName, birthdate);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ContactListPage.BUTTON_ADD_NEW_CONTACT, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(ContactListPage.BUTTON_ADD_NEW_CONTACT),
                WaitUntil.the(AddContactPage.INPUT_FIRST_NAME, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(firstName).into(AddContactPage.INPUT_FIRST_NAME),
                Enter.theValue(lastName).into(AddContactPage.INPUT_LAST_NAME),
                Enter.theValue(birthdate).into(AddContactPage.INPUT_BIRTHDATE),
                Click.on(AddContactPage.BUTTON_SUBMIT)
        );
    }
}
