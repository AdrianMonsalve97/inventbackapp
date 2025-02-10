package com.inventapp.inventApp.domain.dtos.usuario;

import com.inventapp.inventApp.domain.enums.EstadoUsuario;
import com.inventapp.inventApp.domain.enums.Rol;
import com.inventapp.inventApp.domain.models.write.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDTO {

    public UsuarioDTO(Usuario usuario) {
    }

    public UsuarioDTO(String id, String username, String nombre, String password, String email, Rol rol, EstadoUsuario estadoUsuario) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.estadoUsuario = estadoUsuario;
    }

    private String id; 

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Size(min = 3, max = 50)
    private String nombre;

    @NotNull
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @Email
    @NotNull
    private String email;

    @NotNull
    private Rol rol;

    private EstadoUsuario estadoUsuario;

}
