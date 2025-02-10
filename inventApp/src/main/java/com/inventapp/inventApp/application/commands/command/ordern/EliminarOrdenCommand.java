package com.inventapp.inventApp.application.commands.command.ordern;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class EliminarOrdenCommand {
    private UUID id;
}