package com.inventapp.inventApp.domain.service;

import com.inventapp.inventApp.domain.exceptions.UsuarioNoAutorizadoException;
import com.inventapp.inventApp.domain.models.write.Usuario;
import com.inventapp.inventApp.domain.repositories.usuario.IUsuarioRepository;

public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void verificarPermiso(String username) {
        Usuario usuario = usuarioRepository.findByUsernameNative(username)
            .orElseThrow(() -> new UsuarioNoAutorizadoException("Usuario no encontrado o sin permisos"));

        if (!usuario.tienePermiso()) {
            throw new UsuarioNoAutorizadoException("El usuario no tiene permisos suficientes");
        }
    }
}
