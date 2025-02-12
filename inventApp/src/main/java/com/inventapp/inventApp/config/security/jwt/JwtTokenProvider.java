package com.inventapp.inventApp.config.security.jwt;

    import io.jsonwebtoken.*;
    import jakarta.servlet.http.HttpServletRequest;
    import org.springframework.stereotype.Component;

    import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secret;  // Make secret final
    private final long expiration; // Make expiration final

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.secret = jwtProperties.getSecret();       // Initialize from JwtProperties
        this.expiration = jwtProperties.getExpiration(); // Initialize from JwtProperties
    }

    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parserBuilder() // Use parserBuilder
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null; // Or throw an exception if you prefer
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder() // Use parserBuilder
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado");
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Token inválido");
        }
        return false;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Retorna el token sin la parte "Bearer "
        }
        return null;
    }
}