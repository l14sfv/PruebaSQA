package com.pruebas.utils;

public final class EmailGenerator {

    private EmailGenerator() {
    }

    public static String unique(String prefix) {
        return prefix + "_" + System.currentTimeMillis() + "@test.com";
    }
}
