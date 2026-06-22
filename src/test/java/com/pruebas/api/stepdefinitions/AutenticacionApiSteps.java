package com.pruebas.api.stepdefinitions;

import com.pruebas.api.tasks.CreateUserApi;
import com.pruebas.api.tasks.GetUserProfileApi;
import com.pruebas.api.tasks.LoginUserApi;
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
import static org.hamcrest.Matchers.*;

public class AutenticacionApiSteps {

    @Before
    public void setUpStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Cuando("crea un usuario por API con nombre {string} apellido {string} email base {string} y contraseña {string}")
    public void crea_un_usuario_por_api(String nombre, String apellido, String emailBase, String password) {
        String uniqueEmail = emailBase.replace("@", "_" + System.currentTimeMillis() + "@");

        Actor actor = OnStage.theActorCalled("APIUser");
        actor.attemptsTo(
                CreateUserApi.withData(nombre, apellido, uniqueEmail, password)
        );

        actor.remember("generatedEmail", uniqueEmail);
    }

    @Dado("existe un usuario API con nombre {string} apellido {string} email {string} y contraseña {string}")
    public void existe_un_usuario_api(String nombre, String apellido, String email, String password) {
        Actor actor = OnStage.theActorCalled("APIUser");
        actor.attemptsTo(
                CreateUserApi.withData(nombre, apellido, email, password)
        );
    }

    @Cuando("inicia sesión por API con email {string} y contraseña {string}")
    public void inicia_sesion_por_api(String email, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
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

    @Y("el email del perfil debería ser {string}")
    public void el_email_del_perfil_deberia_ser(String emailEsperado) {
        String emailActual = SerenityRest.lastResponse().jsonPath().getString("email");
        assertThat(emailActual, equalTo(emailEsperado));
    }
}
