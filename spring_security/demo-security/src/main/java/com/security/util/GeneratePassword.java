package com.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {
    public static String generatePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
    public static void main(String[] args) {
        System.out.println(generatePassword("123"));
    }
}
