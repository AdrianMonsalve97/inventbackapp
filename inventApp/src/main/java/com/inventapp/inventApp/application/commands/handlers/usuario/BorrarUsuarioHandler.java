package com.inventapp.inventApp.application.commands.handlers.usuario;

import com.inventapp.inventApp.application.commands.command.usuario.BorrarUsuarioCommand;
import com.inventapp.inventApp.application.usecases.usuario.BorrarUsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BorrarUsuarioHandler {

    private final BorrarUsuarioUseCase borrarUsuarioUseCase;

    public void handle(BorrarUsuarioCommand command) {
        borrarUsuarioUseCase.ejecutar(command);
    }
}
