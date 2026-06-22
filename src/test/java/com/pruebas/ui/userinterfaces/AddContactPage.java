package com.pruebas.ui.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class AddContactPage {

    private AddContactPage() {
    }

    public static final Target INPUT_FIRST_NAME = Target.the("campo first name del contacto")
            .located(By.id("firstName"));

    public static final Target INPUT_LAST_NAME = Target.the("campo last name del contacto")
            .located(By.id("lastName"));

    public static final Target INPUT_BIRTHDATE = Target.the("campo birthdate del contacto")
            .located(By.id("birthdate"));

    public static final Target BUTTON_SUBMIT = Target.the("botón submit del contacto")
            .located(By.id("submit"));

    public static final Target BUTTON_CANCEL = Target.the("botón cancelar del contacto")
            .located(By.id("cancel"));
}
