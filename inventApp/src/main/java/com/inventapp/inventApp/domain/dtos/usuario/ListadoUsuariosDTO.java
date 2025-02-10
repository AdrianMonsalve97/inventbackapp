package com.inventapp.inventApp.domain.dtos.usuario;

import com.inventapp.inventApp.domain.enums.EstadoUsuario;
import com.inventapp.inventApp.domain.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListadoUsuariosDTO {
    private UUID id;

    @NotNull(message = "El nombre de usuario no puede ser nulo.")
    private String username;

    @Email(message = "El correo debe ser válido.")
    @NotNull(message = "El correo no puede ser nulo.")
    private String email;

    private Rol rol;
    private EstadoUsuario estadoUsuario;

    // Constructor sin password, ya que no es necesario en el DTO de listado
    public ListadoUsuariosDTO(@NotNull String username, @Email @NotNull String email, Rol rol, EstadoUsuario estadoUsuario) {
        this.username = username;
        this.email = email;
        this.rol = rol;
        this.estadoUsuario = estadoUsuario;
    }
}
