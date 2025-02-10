package com.inventapp.inventApp.application.commands.command.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrarUsuarioCommand {
    private String username;
}
