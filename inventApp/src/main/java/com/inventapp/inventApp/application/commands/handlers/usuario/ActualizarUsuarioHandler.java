package com.inventapp.inventApp.application.commands.handlers.usuario;

import com.inventapp.inventApp.application.commands.command.usuario.ActualizarUsuarioCommand;
import com.inventapp.inventApp.application.usecases.usuario.ActualizarUsuarioUseCase;
import com.inventapp.inventApp.domain.dtos.usuario.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ActualizarUsuarioHandler {

    private final ActualizarUsuarioUseCase actualizarUsuarioUseCase;

    @Transactional
    public UsuarioDTO ejecutar(ActualizarUsuarioCommand command) {
        return actualizarUsuarioUseCase.ejecutar(command);
    }
}
