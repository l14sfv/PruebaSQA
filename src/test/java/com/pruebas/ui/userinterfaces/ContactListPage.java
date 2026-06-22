package com.pruebas.ui.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ContactListPage {

        private ContactListPage() {
        }

        public static final Target BUTTON_ADD_NEW_CONTACT = Target.the("botón Add a New Contact")
                .located(By.id("add-contact"));

        public static final Target BUTTON_LOGOUT = Target.the("botón Logout")
                .located(By.id("logout"));

        public static final Target CONTACT_TABLE = Target.the("tabla de contactos")
                .located(By.tagName("table"));

        public static Target CONTACT_NAME(String fullName) {
                return Target.the("contacto en la lista: " + fullName)
                        .located(By.xpath("//*[contains(text(),'" + fullName + "')]"));
        }
}
