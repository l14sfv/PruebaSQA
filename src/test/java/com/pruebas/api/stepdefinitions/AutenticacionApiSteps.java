package com.pruebas.api.stepdefinitions;

import com.pruebas.api.tasks.CreateUserApi;
import com.pruebas.api.tasks.GetUserProfileApi;
import com.pruebas.api.tasks.LoginUserApi;
import com.pruebas.utils.EmailGenerator;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

public class AutenticacionApiSteps {

    @Before
    public void setUpStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Cuando("crea un usuario por API con nombre {string} apellido {string} y contraseña {string}")
    public void crea_un_usuario_por_api(String nombre, String apellido, String password) {
        Actor actor = OnStage.theActorCalled("APIUser");
        String email = EmailGenerator.unique("apiuser");

        actor.remember("generatedEmail", email);

        actor.attemptsTo(
                CreateUserApi.withData(nombre, apellido, email, password)
        );
    }

    @Dado("existe un usuario API con nombre {string} apellido {string} y contraseña {string}")
    public void existe_un_usuario_api(String nombre, String apellido, String password) {
        Actor actor = OnStage.theActorCalled("APIUser");
        String email = EmailGenerator.unique("apiuser");

        actor.remember("generatedEmail", email);
        actor.remember("generatedPassword", password);

        actor.attemptsTo(
                CreateUserApi.withData(nombre, apellido, email, password)
        );
    }

    @Cuando("inicia sesión por API con la contraseña {string}")
    public void inicia_sesion_por_api(String password) {
        Actor actor = OnStage.theActorInTheSpotlight();
        String email = actor.recall("generatedEmail");

        actor.attemptsTo(
                LoginUserApi.withCredentials(email, password)
        );
    }

    @Cuando("consulta su perfil por API")
    public void consulta_su_perfil_por_api() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                GetUserProfileApi.information()
        );
    }

    @Entonces("la respuesta API debería ser {int}")
    public void la_respuesta_api_deberia_ser(Integer statusCode) {
        SerenityRest.lastResponse().then().statusCode(statusCode);
    }

    @Y("debería recibir un token de autenticación")
    public void deberia_recibir_un_token_de_autenticacion() {
        String token = OnStage.theActorInTheSpotlight().recall("token");
        assertThat(token, not(isEmptyOrNullString()));
    }

    @Y("el email del perfil debería ser el generado")
    public void el_email_del_perfil_deberia_ser_el_generado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        String expectedEmail = actor.recall("generatedEmail");
        String actualEmail = SerenityRest.lastResponse().jsonPath().getString("email");

        assertThat(actualEmail, equalTo(expectedEmail));
    }
}
