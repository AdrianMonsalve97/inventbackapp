package com.inventapp.inventApp.application.commands.command.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class EliminarClienteCommand {
    private UUID id;
}