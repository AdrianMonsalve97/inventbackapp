package com.inventapp.inventApp.config.security.jwt;

    import com.inventapp.inventApp.domain.exceptions.UsuarioNoAutorizadoException;
    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.JwtException;
    import io.jsonwebtoken.Jwts;
    import jakarta.servlet.http.HttpServletRequest;
    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.context.annotation.Configuration;

    import lombok.*;

    @Configuration
    @ConfigurationProperties(prefix = "jwt")
    @Getter
    @Setter
    public class JwtProperties {
        private String secret;
        private long expiration;
        public String resolveToken(HttpServletRequest request) {
            String bearerToken = request.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            }
            return null;
        }

        public boolean validateToken(String token) {
            try {
                Jwts.parser().setSigningKey(secret).build().parse(token);
                return true;
            } catch (JwtException | IllegalArgumentException e) {
                throw new UsuarioNoAutorizadoException("Token inválido o expirado");
            }
        }

        public String getUsernameFromToken(String token) {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        }
    }