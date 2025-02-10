package com.inventapp.inventApp.presentation.usuario.controllers.readcontrollers;

import com.inventapp.inventApp.application.queries.handlers.usuario.ListarUsuariosHandlers;
import com.inventapp.inventApp.application.queries.queries.usuario.ListarUsuariosQuery;
import com.inventapp.inventApp.domain.dtos.usuario.ListadoUsuariosDTO;
import com.inventapp.inventApp.domain.enums.EstadoUsuario;
import com.inventapp.inventApp.domain.enums.Rol;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioReadController {

    private final ListarUsuariosHandlers listadoUsuariosHandler;

    public UsuarioReadController(ListarUsuariosHandlers listadoUsuariosHandler) {
        this.listadoUsuariosHandler = listadoUsuariosHandler;
    }
    /**
     * Endpoint para listar usuarios con filtros opcionales.
     *
     * @param username       Nombre de usuario (opcional).
     * @param email          Email del usuario (opcional).
     * @param rol            Rol del usuario (opcional).
     * @param estadoUsuario  Estado del usuario (opcional).
     * @return Lista de usuarios que cumplen con los filtros proporcionados.
     */
    @GetMapping("/listar")
    public List<ListadoUsuariosDTO> listarUsuarios(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Rol rol,
            @RequestParam(required = false) EstadoUsuario estadoUsuario) {
        ListarUsuariosQuery query = new ListarUsuariosQuery(username, email, rol, estadoUsuario);
        return listadoUsuariosHandler.handle(query);
    }
}
