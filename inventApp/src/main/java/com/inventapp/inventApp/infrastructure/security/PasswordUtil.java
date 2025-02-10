package com.inventapp.inventApp.infrastructure.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean matches(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}

