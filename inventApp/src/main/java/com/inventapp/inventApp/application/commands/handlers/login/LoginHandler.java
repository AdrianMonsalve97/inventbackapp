package com.inventapp.inventApp.application.commands.handlers.login;

import com.inventapp.inventApp.application.usecases.usuario.ObtenerUsuarioPorUsernameUseCase;
import com.inventapp.inventApp.domain.dtos.login.LoginCommand;
import com.inventapp.inventApp.domain.dtos.usuario.LoginResponse;
import com.inventapp.inventApp.domain.dtos.usuario.UsuarioLogDTO;
import com.inventapp.inventApp.infrastructure.security.AuthService;
import org.springframework.stereotype.Component;

@Component
public class LoginHandler {

    private final AuthService authService;
    private final ObtenerUsuarioPorUsernameUseCase obtenerUsuarioPorUsernameUseCase;

    public LoginHandler(AuthService authService, ObtenerUsuarioPorUsernameUseCase obtenerUsuarioPorUsernameUseCase) {
        this.authService = authService;
        this.obtenerUsuarioPorUsernameUseCase = obtenerUsuarioPorUsernameUseCase;
    }

    public LoginResponse handle(LoginCommand command) {
        return authService.login(command.getUsername(), command.getPassword());
    }

    public UsuarioLogDTO obtenerUsuario(LoginCommand command) {
        return obtenerUsuarioPorUsernameUseCase.ejecutar(command.getUsername());
    }
}