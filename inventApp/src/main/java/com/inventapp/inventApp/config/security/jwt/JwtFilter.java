package com.inventapp.inventApp.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        Optional<String> tokenOptional = Optional.ofNullable(request.getHeader("Authorization"));

        // Si no se encuentra en "Authorization", intenta buscar en "Authorization"
        if (tokenOptional.isEmpty()) {
            tokenOptional = Optional.ofNullable(request.getHeader("Authorization"))
                    .filter(header -> header.startsWith("Bearer ")) // Filtra solo los encabezados de tipo Bearer
                    .map(header -> header.substring(7)); // Eliminar el "Bearer " para obtener solo el token
        }

        tokenOptional.ifPresent(token -> {
            try {
                byte[] secretKeyDecoded = Base64.getDecoder().decode(jwtProperties.getSecret());

                Claims claims = Jwts.parser()
                        .setSigningKey(Keys.hmacShaKeyFor(secretKeyDecoded)) // Usamos la clave secreta para verificar la firma
                        .build() // Construimos el parser
                        .parseClaimsJws(token) // Analizamos el token JWT
                        .getBody(); // Obtenemos el cuerpo de las reclamaciones

                // Verifica si el token está expirado
                if (claims.getExpiration().before(new java.util.Date())) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token ha expirado");
                    return;
                }

                setAuthentication(claims);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                try {
                    response.getWriter().write("Token no válido o expirado");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(Claims claims) {
        String username = claims.get("username", String.class);
        String rol = claims.get("rol", String.class);

        // Definir las autoridades con el rol si existe
        List<SimpleGrantedAuthority> authorities = rol != null ?
                List.of(new SimpleGrantedAuthority("ROLE_" + rol.toUpperCase())) :
                Collections.emptyList();

        // Crear un objeto de autenticación y establecerlo en el contexto de seguridad
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
