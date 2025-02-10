package com.inventapp.inventApp.application.commands.command.producto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrarProductoCommand {
    @NotNull
    private UUID id;
}
