package com.inventapp.inventApp.infrastructure.security;

import java.util.Date;
import java.util.Base64;

import com.inventapp.inventApp.application.usecases.usuario.ObtenerUsuarioPorUsernameUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inventapp.inventApp.domain.dtos.usuario.UsuarioLogDTO;
import com.inventapp.inventApp.config.security.jwt.JwtProperties;
import com.inventapp.inventApp.domain.exceptions.InvalidCredentialsException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ObtenerUsuarioPorUsernameUseCase obtenerUsuarioPorUsernameUseCase;
    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;

    // Método para login
    public String login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidCredentialsException("El nombre de usuario no puede estar vacío.");
        }
        UsuarioLogDTO usuarioDTO = obtenerUsuarioPorUsernameUseCase.ejecutar(username);
        System.out.println("Usuario encontrado: " + usuarioDTO.getUsername());

        String storedHashedPassword = usuarioDTO.getPassword();

        if (PasswordUtil.matches(password, storedHashedPassword)) {
            System.out.println("Contraseña correcta");
            return generateJwtToken(usuarioDTO);
        } else {
            System.out.println("Contraseña incorrecta");
            throw new InvalidCredentialsException("Contraseña incorrecta");
        }
    }




    // Método para generar el token JWT
    private String generateJwtToken(UsuarioLogDTO usuarioLogDTO) {
        long expirationTime = jwtProperties.getExpiration(); // Obtener tiempo de expiración desde el archivo de configuración

        return Jwts.builder()
                .setSubject(usuarioLogDTO.getUsername())
                .claim("username", usuarioLogDTO.getUsername())
                .claim("rol", usuarioLogDTO.getRol()) // Asegúrate de incluir el rol del usuario
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtProperties.getSecret())), SignatureAlgorithm.HS256)
                .compact();
    }
}
