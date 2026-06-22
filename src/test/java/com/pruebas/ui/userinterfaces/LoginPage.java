package com.pruebas.ui.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

        private LoginPage() {
        }

        public static final Target INPUT_EMAIL = Target.the("campo de email en login")
                .located(By.id("email"));

        public static final Target INPUT_PASSWORD = Target.the("campo de contraseña en login")
                .located(By.id("password"));

        public static final Target BUTTON_SUBMIT = Target.the("botón submit de login")
                .located(By.id("submit"));

        public static final Target BUTTON_SIGNUP = Target.the("botón para ir al registro")
                .located(By.id("signup"));

        public static final Target TITLE_CONTACT_LIST = Target.the("título Contact List App")
                .located(By.xpath("//h1[contains(text(),'Contact List App')]"));

        public static final Target MESSAGE_ERROR = Target.the("mensaje de error de autenticación")
                .located(By.id("error"));

        public static final Target CONTACT_TABLE = Target.the("tabla de contactos después del login")
                .located(By.xpath("//table"));

        public static final Target BUTTON_LOGOUT = Target.the("botón logout")
                .located(By.id("logout"));

        public static final Target BUTTON_ADD_CONTACT = Target.the("botón add a new contact")
                .located(By.id("add-contact"));

        public static final Target HEADER_CONTACT_LIST = Target.the("encabezado de lista de contactos")
                .located(By.xpath("//h1[contains(text(),'Contact List')]"));
}