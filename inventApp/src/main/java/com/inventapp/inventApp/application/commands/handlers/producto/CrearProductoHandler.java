package com.inventapp.inventApp.application.commands.handlers.producto;

import com.inventapp.inventApp.application.commands.command.producto.CrearProductoCommand;
import com.inventapp.inventApp.domain.models.write.Empresa;
import com.inventapp.inventApp.domain.models.write.Producto;
import com.inventapp.inventApp.domain.repositories.empresa.IEmpresaRepository;
import com.inventapp.inventApp.domain.repositories.producto.IProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrearProductoHandler {

    private final IProductoRepository productoRepository;
    private final IEmpresaRepository empresaRepository;

    @Transactional
    public Producto ejecutar(CrearProductoCommand command) {
        Empresa empresa = empresaRepository.findByNit(command.getEmpresaNit())
                .orElseThrow(() -> new RuntimeException("No se encontró la empresa con el NIT: " + command.getEmpresaNit()));

        Producto producto = Producto.builder()
                .codigo(command.getCodigo())
                .nombre(command.getNombre())
                .caracteristicas(command.getCaracteristicas())
                .precio(command.getPrecio())
                .moneda(command.getMoneda())
                .empresa(empresa)
                .build();

        return productoRepository.save(producto);
    }
}
