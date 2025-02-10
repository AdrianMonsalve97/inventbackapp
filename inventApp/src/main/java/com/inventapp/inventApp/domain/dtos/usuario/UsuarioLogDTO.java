package com.inventapp.inventApp.domain.dtos.usuario;

import com.inventapp.inventApp.domain.enums.EstadoUsuario;
import com.inventapp.inventApp.domain.enums.Rol;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioLogDTO {
    private UUID id;
    private String username;
    private String email;
    private Rol rol;
    private String password;
    private EstadoUsuario estadoUsuario;

    public UsuarioLogDTO(@NotNull String username, String password, Rol rol
    ) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }


}


