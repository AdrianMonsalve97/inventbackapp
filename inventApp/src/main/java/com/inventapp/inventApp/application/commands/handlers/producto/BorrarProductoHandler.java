package com.inventapp.inventApp.application.commands.handlers.producto;

import com.inventapp.inventApp.application.usecases.producto.BorrarProductoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BorrarProductoHandler {

    private final BorrarProductoUseCase borrarProductoUseCase;

    public void ejecutar(UUID idProducto) {
        borrarProductoUseCase.ejecutar(idProducto);
    }
}
