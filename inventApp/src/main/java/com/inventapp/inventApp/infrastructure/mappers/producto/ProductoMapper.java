package com.inventapp.inventApp.infrastructure.mappers.producto;

import com.inventapp.inventApp.domain.dtos.producto.ProductoDTO;
import com.inventapp.inventApp.domain.models.write.Categoria;
import com.inventapp.inventApp.domain.models.write.Empresa;
import com.inventapp.inventApp.domain.models.write.Producto;
import com.inventapp.inventApp.application.commands.command.producto.CrearProductoCommand;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductoMapper {

    public Producto toEntity(CrearProductoCommand command, Empresa empresa, Set<Categoria> categorias, double precioConvertido) {
        return Producto.builder()
                .codigo(command.getCodigo())
                .nombre(command.getNombre())
                .caracteristicas(command.getCaracteristicas())
                .precio(command.getPrecio())
                .moneda(command.getMoneda())
                .precioConvertido(precioConvertido)
                .empresa(empresa)
                .categorias(categorias)
                .activo(true)
                .build();
    }

    public ProductoDTO toDTO(Producto producto) {
        return ProductoDTO.builder()
                .id(producto.getId())
                .codigo(producto.getCodigo())
                .nombre(producto.getNombre())
                .caracteristicas(producto.getCaracteristicas())
                .precio(producto.getPrecio())
                .moneda(producto.getMoneda())
                .precioConvertido(producto.getPrecioConvertido())
                .empresaNit(producto.getEmpresa().getNit())
                .categorias(producto.getCategorias().stream().map(Categoria::getNombre).collect(Collectors.toSet()))
                .activo(producto.isActivo())
                .build();
    }
}