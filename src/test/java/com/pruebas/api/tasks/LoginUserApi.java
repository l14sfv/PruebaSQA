package com.pruebas.api.tasks;

import com.pruebas.api.utils.ApiConfig;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginUserApi implements Task {

    private final String email;
    private final String password;

    public LoginUserApi(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static LoginUserApi withCredentials(String email, String password) {
        return instrumented(LoginUserApi.class, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        SerenityRest.given()
                .baseUri(ApiConfig.BASE_URL)
                .contentType("application/json")
                .body(body)
                .post(ApiConfig.LOGIN);

        String responseBody = SerenityRest.lastResponse().getBody().asString();
        String token = null;

        try {
            JsonPath jsonPath = new JsonPath(responseBody);
            token = jsonPath.getString("token");
        } catch (Exception ignored) {
        }

        actor.remember("token", token);
    }
}
