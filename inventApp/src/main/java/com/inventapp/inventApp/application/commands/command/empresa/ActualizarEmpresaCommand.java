package com.inventapp.inventApp.application.commands.command.empresa;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ActualizarEmpresaCommand {

    @NotNull(message = "El ID de la empresa es obligatorio")
    private UUID id;

    @NotNull(message = "El NIT es obligatorio")
    @Size(min = 8, max = 15, message = "El NIT debe tener entre 8 y 15 caracteres")
    private String nit;

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La dirección es obligatoria")
    @Size(min = 5, max = 200, message = "La dirección debe tener entre 5 y 200 caracteres")
    private String direccion;

    @NotNull(message = "El teléfono es obligatorio")
    @Size(min = 7, max = 20, message = "El teléfono debe tener entre 7 y 20 caracteres")
    private String telefono;
}
