package com.inventapp.inventApp.application.commands.command.categoria;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarCategoriaCommand {

    @NotNull
    private UUID id;

    @NotNull
    @Size(min = 2, max = 50)
    private String nombre;
}
