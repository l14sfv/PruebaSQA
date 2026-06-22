package com.pruebas.api.utils;

public class ApiConfig {

    private ApiConfig() {
    }

    public static final String BASE_URL = "https://thinking-tester-contact-list.herokuapp.com";
    public static final String USERS = "/users";
    public static final String LOGIN = "/users/login";
    public static final String PROFILE = "/users/me";
    public static final String LOGOUT = "/users/logout";
    public static final String CONTACTS = "/contacts";
}