package com.inventapp.inventApp.application.usecases.producto;

import com.inventapp.inventApp.domain.repositories.producto.IProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BorrarProductoUseCase {

    private final IProductoRepository productoRepository;

    @Transactional
    public void ejecutar(UUID idProducto) {
        if (!productoRepository.existsById(idProducto)) {
            throw new EntityNotFoundException("Producto no encontrado");
        }
        productoRepository.deleteById(idProducto);
    }
}
