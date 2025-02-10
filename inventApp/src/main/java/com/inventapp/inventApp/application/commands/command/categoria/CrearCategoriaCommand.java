package com.inventapp.inventApp.application.commands.command.categoria;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearCategoriaCommand {

    @NotNull
    @Size(min = 2, max = 50)
    private String nombre;
}
