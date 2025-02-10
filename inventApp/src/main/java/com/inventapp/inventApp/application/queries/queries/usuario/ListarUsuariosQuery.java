package com.inventapp.inventApp.application.queries.queries.usuario;


import com.inventapp.inventApp.domain.enums.EstadoUsuario;
import com.inventapp.inventApp.domain.enums.Rol;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarUsuariosQuery {

    private String username;
    private String email;
    private Rol rol;
    private EstadoUsuario estadoUsuario;

    public ListarUsuariosQuery(String username, String email, Rol rol, EstadoUsuario estadoUsuario) {
        this.username = username;
        this.email = email;
        this.rol = rol;
        this.estadoUsuario = estadoUsuario;
    }
    public ListarUsuariosQuery() {}
}
