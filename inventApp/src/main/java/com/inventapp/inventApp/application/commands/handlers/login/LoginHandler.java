package com.inventapp.inventApp.application.commands.handlers.login;

import com.inventapp.inventApp.domain.dtos.login.LoginCommand;
import com.inventapp.inventApp.domain.exceptions.InvalidCredentialsException;
import com.inventapp.inventApp.infrastructure.security.AuthService;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LoginHandler {

    private final AuthService authService;
    private static final Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    public LoginHandler(AuthService authService) {
        this.authService = authService;
    }

    public String handle(LoginCommand command) {
        try {
            String token = authService.login(command.getUsername(), command.getPassword());
            return token;
        } catch (InvalidCredentialsException e) {
            logger.error("Intento de inicio de sesión fallido para el usuario: {}", command.getUsername());
            throw e;  
        }
    }
}
