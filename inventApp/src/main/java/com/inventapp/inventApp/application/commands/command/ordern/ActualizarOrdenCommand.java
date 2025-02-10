package com.inventapp.inventApp.application.commands.command.ordern;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class ActualizarOrdenCommand {
    private UUID id;
    private List<UUID> productoIds;
    private UUID clienteId;
}