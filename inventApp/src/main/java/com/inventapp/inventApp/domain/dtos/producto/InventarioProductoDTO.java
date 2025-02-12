package com.inventapp.inventApp.domain.dtos.producto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioProductoDTO {
    private String codigo;
    private String nombre;
    private String caracteristicas;
    private double precio;
    private String moneda;
    private long cantidad;
    private String empresaNit;
    private Set<String> categorias;
    private boolean activo;
}
