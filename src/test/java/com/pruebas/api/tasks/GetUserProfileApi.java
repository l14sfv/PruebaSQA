package com.pruebas.api.tasks;

import com.pruebas.api.utils.ApiConfig;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class GetUserProfileApi implements Task {

    public static GetUserProfileApi information() {
        return new GetUserProfileApi();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String token = actor.recall("token");

        SerenityRest.given()
                .baseUri(ApiConfig.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .get(ApiConfig.PROFILE);
    }
}
