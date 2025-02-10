package com.inventapp.inventApp.application.commands.handlers.producto;

import com.inventapp.inventApp.application.commands.command.producto.ActualizarProductoCommand;
import com.inventapp.inventApp.application.usecases.producto.ActualizarProductoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActualizarProductoHandler {

    private final ActualizarProductoUseCase actualizarProductoUseCase;

    public void handle(ActualizarProductoCommand command) {
        actualizarProductoUseCase.ejecutar(command);
    }
}