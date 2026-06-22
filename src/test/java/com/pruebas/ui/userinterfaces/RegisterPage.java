package com.pruebas.ui.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RegisterPage {

        private RegisterPage() {
        }

        public static final Target INPUT_FIRST_NAME = Target.the("campo first name en registro")
                .located(By.id("firstName"));

        public static final Target INPUT_LAST_NAME = Target.the("campo last name en registro")
                .located(By.id("lastName"));

        public static final Target INPUT_EMAIL = Target.the("campo email en registro")
                .located(By.id("email"));

        public static final Target INPUT_PASSWORD = Target.the("campo password en registro")
                .located(By.id("password"));

        public static final Target BUTTON_SUBMIT = Target.the("botón submit de registro")
                .located(By.id("submit"));

        public static final Target BUTTON_CANCEL = Target.the("botón cancel de registro")
                .located(By.id("cancel"));

        public static final Target TITLE_ADD_USER = Target.the("título add user")
                .located(By.xpath("//h1[contains(text(),'Add User')]"));

        public static final Target MESSAGE_ERROR = Target.the("mensaje de error en registro")
                .located(By.id("error"));

        public static final Target FOOTER_COPYRIGHT = Target.the("texto de copyright del formulario")
                .located(By.xpath("//*[contains(text(),'Created by Kristin Jackvony')]"));
}