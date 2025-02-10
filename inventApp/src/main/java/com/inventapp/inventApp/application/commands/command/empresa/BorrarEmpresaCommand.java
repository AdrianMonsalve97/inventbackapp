package com.inventapp.inventApp.application.commands.command.empresa;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BorrarEmpresaCommand {

    @NotBlank(message = "El nombre de la empresa no puede estar vacío")
    private String nombre;
}
