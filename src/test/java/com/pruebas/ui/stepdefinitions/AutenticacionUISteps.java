package com.pruebas.ui.stepdefinitions;

import com.pruebas.ui.tasks.CreateNewContact;
import com.pruebas.ui.tasks.LoginWithCredentials;
import com.pruebas.ui.tasks.Logout;
import com.pruebas.ui.tasks.OpenTheApplication;
import com.pruebas.ui.tasks.RegisterNewUser;
import com.pruebas.ui.userinterfaces.ContactListPage;
import com.pruebas.ui.userinterfaces.LoginPage;
import com.pruebas.utils.EmailGenerator;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

public class AutenticacionUISteps {

    @Before
    public void setUpStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("que {word} abre la aplicación de Contact List")
    public void que_abre_la_aplicacion_de_contact_list(String actorName) {
        OnStage.theActorCalled(actorName).can(BrowseTheWeb.with(Serenity.getDriver()));
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenTheApplication.onLoginPage()
        );
    }

    @Cuando("se registra con nombre {string} apellido {string} y contraseña {string}")
    public void se_registra_con_datos(String firstName, String lastName, String password) {
        Actor actor = OnStage.theActorInTheSpotlight();
        String email = EmailGenerator.unique("uiuser");

        actor.remember("generatedEmail", email);

        actor.attemptsTo(
                RegisterNewUser.withData(firstName, lastName, email, password)
        );
    }

    @Entonces("debería ver un mensaje de registro exitoso")
    public void deberia_ver_un_mensaje_de_registro_exitoso() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(WebElementQuestion.the(ContactListPage.BUTTON_ADD_NEW_CONTACT), isVisible())
        );
    }

    @Dado("que existe un usuario registrado con contraseña {string}")
    public void que_existe_un_usuario_registrado(String password) {
        Actor actor = OnStage.theActorInTheSpotlight();
        String email = EmailGenerator.unique("uiuser");

        actor.remember("generatedEmail", email);

        actor.attemptsTo(
                OpenTheApplication.onLoginPage(),
                RegisterNewUser.withData("Usuario", "Registrado", email, password),
                Logout.fromApplication()
        );
    }

    @Cuando("inicia sesión con la contraseña {string}")
    public void inicia_sesion_con_la_contrasena(String password) {
        Actor actor = OnStage.theActorInTheSpotlight();
        String email = actor.recall("generatedEmail");

        actor.attemptsTo(
                LoginWithCredentials.using(email, password)
        );
    }

    @Entonces("debería ver la lista de contactos")
    public void deberia_ver_la_lista_de_contactos() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(WebElementQuestion.the(ContactListPage.BUTTON_ADD_NEW_CONTACT), isVisible())
        );
    }

    @Entonces("debería ver un mensaje de error de credenciales inválidas")
    public void deberia_ver_mensaje_de_error_credenciales_invalidas() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(LoginPage.MESSAGE_ERROR, isVisible()).forNoMoreThan(10).seconds()
        );

        OnStage.theActorInTheSpotlight().should(
                seeThat("el mensaje de error de login",
                        Text.of(LoginPage.MESSAGE_ERROR),
                        containsString("Incorrect username or password"))
        );
    }

    @Y("no debería ver la lista de contactos")
    public void no_deberia_ver_la_lista_de_contactos() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(WebElementQuestion.the(ContactListPage.BUTTON_ADD_NEW_CONTACT), not(isVisible()))
        );
    }

    @Cuando("cierra sesión")
    public void cierra_sesion() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Logout.fromApplication()
        );
    }

    @Entonces("debería ver el formulario de login")
    public void deberia_ver_el_formulario_de_login() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(WebElementQuestion.the(LoginPage.INPUT_EMAIL), isVisible())
        );
    }

    @Cuando("crea un contacto con nombre {string} apellido {string} fechaNacimiento {string}")
    public void crea_un_contacto_con_nombre_apellido_fecha_nacimiento(String nombre, String apellido, String fechaNacimiento) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CreateNewContact.withData(nombre, apellido, fechaNacimiento)
        );
    }

    @Entonces("debería ver el contacto {string} en la lista")
    public void deberia_ver_el_contacto_en_la_lista(String nombreCompleto) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(WebElementQuestion.the(ContactListPage.CONTACT_NAME(nombreCompleto)), isVisible())
        );
    }
}
