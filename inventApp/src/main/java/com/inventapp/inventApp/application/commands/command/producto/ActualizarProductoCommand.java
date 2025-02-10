package com.inventapp.inventApp.application.commands.command.producto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarProductoCommand {

    @NotNull
    private UUID id;

    @NotNull
    @Size(min = 2, max = 100)
    private String nombre;

    @NotNull
    @Positive
    private double precio;

    @NotNull
    private UUID empresaId;

    private Set<UUID> categoriasIds;
}
