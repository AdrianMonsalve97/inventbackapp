package com.inventapp.inventApp.domain.dtos.producto;

import com.inventapp.inventApp.domain.models.write.Categoria;
import com.inventapp.inventApp.domain.models.write.Producto;
import com.inventapp.inventApp.domain.models.write.Empresa;
import lombok.*;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {

    private UUID id;
    private String codigo;
    private String nombre;
    private String caracteristicas;
    private double precio;
    private String moneda;
    private double precioConvertido;
    private String empresaNit;
    private Set<String> categorias;
    private boolean activo;

    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.codigo = producto.getCodigo();
        this.nombre = producto.getNombre();
        this.caracteristicas = producto.getCaracteristicas();
        this.precio = producto.getPrecio();
        this.moneda = producto.getMoneda();
        this.precioConvertido = producto.getPrecioConvertido();

        this.empresaNit = (producto.getEmpresa() != null) ? producto.getEmpresa().getNit() : null;

        this.categorias = producto.getCategorias() != null
                ? producto.getCategorias().stream()
                .map(Categoria::getNombre)
                .collect(Collectors.toSet())
                : Collections.emptySet();

        this.activo = producto.isActivo();
    }
}
