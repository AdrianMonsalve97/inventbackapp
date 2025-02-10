package com.inventapp.inventApp.config.security.jwt;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    
    private final JwtProperties jwtProperties;

    public String getSecretKey() {
        return jwtProperties.getSecret();
    }

    public long getExpirationTime() {
        return jwtProperties.getExpiration();
    }
}