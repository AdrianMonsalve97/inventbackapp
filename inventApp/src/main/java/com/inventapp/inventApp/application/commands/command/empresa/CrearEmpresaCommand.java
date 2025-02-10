package com.inventapp.inventApp.application.commands.command.empresa;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CrearEmpresaCommand {
    private UUID id;
    private String nit;
    private String nombre;
    private String direccion;
    private String telefono;
    private UUID adminId;
}
