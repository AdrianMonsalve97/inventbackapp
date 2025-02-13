package com.inventapp.inventApp.infrastructure.security;

import com.inventapp.inventApp.application.usecases.usuario.ObtenerUsuarioPorUsernameUseCase;
import com.inventapp.inventApp.config.security.jwt.JwtProperties;
import com.inventapp.inventApp.domain.dtos.usuario.LoginResponse;
import com.inventapp.inventApp.domain.dtos.usuario.UsuarioLogDTO;
import com.inventapp.inventApp.domain.exceptions.InvalidCredentialsException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ObtenerUsuarioPorUsernameUseCase obtenerUsuarioPorUsernameUseCase;
    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;

    public LoginResponse login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidCredentialsException("El nombre de usuario no puede estar vacío.");
        }
        UsuarioLogDTO usuarioLogDTO = obtenerUsuarioPorUsernameUseCase.ejecutar(username);
        if (usuarioLogDTO == null) {
            throw new InvalidCredentialsException("Credenciales inválidas");
        }
        if (passwordEncoder.matches(password, usuarioLogDTO.getPassword())) {
            String token = generateJwtToken(usuarioLogDTO);
            return new LoginResponse(usuarioLogDTO.getId().toString(), token, usuarioLogDTO.getRol().toString());
        } else {
            throw new InvalidCredentialsException("Contraseña incorrecta");
        }
    }

    private String generateJwtToken(UsuarioLogDTO usuarioLogDTO) {
        long expirationTime = jwtProperties.getExpiration();

        return Jwts.builder()
                .setSubject(usuarioLogDTO.getUsername())
                .claim("username", usuarioLogDTO.getUsername())
                .claim("rol", usuarioLogDTO.getRol().getNombre().toUpperCase()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtProperties.getSecret())))
                .compact();
    }
}