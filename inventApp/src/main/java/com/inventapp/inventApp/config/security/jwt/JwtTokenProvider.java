package com.inventapp.inventApp.config.security.jwt;

    import io.jsonwebtoken.*;
    import jakarta.servlet.http.HttpServletRequest;
    import org.springframework.stereotype.Component;

    import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secret;
    private final long expiration;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.secret = jwtProperties.getSecret();
        this.expiration = jwtProperties.getExpiration();
    }

    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
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
            return bearerToken.substring(7);
        }
        return null;
    }
}