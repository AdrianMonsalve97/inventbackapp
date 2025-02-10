package com.inventapp.inventApp.config.security.jwt;

    import io.jsonwebtoken.*;
    import jakarta.servlet.http.HttpServletRequest;
    import org.springframework.stereotype.Component;

    import java.util.Date;

    @Component
    public class JwtTokenProvider {

        private String secret;
        private long expiration;


        public String getUsernameFromToken(String token) {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }

        // Validar el token JWT
        public boolean validateToken(String token) {
            try {
                // Verifica que el token esté firmado correctamente y no haya expirado
                Jwts.parser()
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

        // Resolver el token JWT desde la cabecera de la solicitud
        public String resolveToken(HttpServletRequest request) {
            String bearerToken = request.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7); // Retorna el token sin la parte "Bearer "
            }
            return null;
        }
    }