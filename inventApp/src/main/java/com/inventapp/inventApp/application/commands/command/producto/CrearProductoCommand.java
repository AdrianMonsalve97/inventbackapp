package com.inventapp.inventApp.application.commands.command.producto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearProductoCommand {
    private String codigo;
    private String nombre;
    private String caracteristicas;
    private double precio;
    private String moneda;

    private String empresaId;
    private Set<String> categorias;
}
