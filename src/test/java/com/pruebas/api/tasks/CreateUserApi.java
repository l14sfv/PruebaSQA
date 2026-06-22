package com.pruebas.api.tasks;

import com.pruebas.api.utils.ApiConfig;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateUserApi implements Task {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public CreateUserApi(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public static CreateUserApi withData(String firstName, String lastName, String email, String password) {
        return instrumented(CreateUserApi.class, firstName, lastName, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<String, Object> body = new HashMap<>();
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("email", email);
        body.put("password", password);

        SerenityRest.given()
            .log().all()
            .baseUri(ApiConfig.BASE_URL)
            .contentType("application/json")
            .body(body)
            .post(ApiConfig.USERS);

        SerenityRest.lastResponse().then().log().all();

        actor.remember("email", email);
        actor.remember("password", password);
    }
}