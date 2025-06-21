package com.redsegura.utils;

public class ValidationUtils {

    public static boolean isValidUsername(String username) {
        return username != null && username.trim().length() >= 3;
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.trim().length() >= 6;
    }
}
