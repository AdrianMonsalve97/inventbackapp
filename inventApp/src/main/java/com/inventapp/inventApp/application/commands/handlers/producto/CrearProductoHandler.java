package com.inventapp.inventApp.application.commands.handlers.producto;

import com.inventapp.inventApp.application.commands.command.producto.CrearProductoCommand;
import com.inventapp.inventApp.application.usecases.producto.CrearProductoUseCase;
import com.inventapp.inventApp.domain.dtos.producto.ProductoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CrearProductoHandler {

    private final CrearProductoUseCase crearProductoUseCase;

    public ProductoDTO handle(CrearProductoCommand command) {
        return crearProductoUseCase.ejecutar(command);
    }
}
