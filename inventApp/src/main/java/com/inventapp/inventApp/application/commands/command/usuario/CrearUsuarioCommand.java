package com.inventapp.inventApp.application.commands.command.usuario;
import com.inventapp.inventApp.domain.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class CrearUsuarioCommand {

    private UUID id;

    @NotNull
    @Size(min = 2, max = 50)
    private String username;

    @NotNull
    @Size(min = 8)
    private String password;

    @Email
    @NotNull
    private String email;

    @NotNull
    private Rol rol;

    @NotNull
    private String nombre;
}


