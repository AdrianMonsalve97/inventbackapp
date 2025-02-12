package com.inventapp.inventApp.config.security.jwt;

import com.inventapp.inventApp.domain.exceptions.UsuarioNoAutorizadoException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {
    private String secret;
    private long expiration;

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parse(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new UsuarioNoAutorizadoException("Token inválido o expirado");
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new UsuarioNoAutorizadoException("Token inválido o expirado");
        }
    }

    public String getRoleFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("rol", String.class);
        } catch (JwtException | IllegalArgumentException e) {
            throw new UsuarioNoAutorizadoException("Token inválido o expirado");
        }
    }
}