package com.inventapp.inventApp.application.queries.handlers.usuario;

import com.inventapp.inventApp.domain.dtos.usuario.UsuarioLogDTO;
import com.inventapp.inventApp.application.queries.queries.usuario.ObtenerUsuarioPorUsernameQuery;
import com.inventapp.inventApp.domain.exceptions.UsuarioNoEncontradoException;
import com.inventapp.inventApp.domain.models.write.Usuario;
import com.inventapp.inventApp.domain.repositories.usuario.IUsuarioRepository;

import java.util.Optional;

public class ObtenerUsuarioPorUsernameHandler {
    private final IUsuarioRepository iUsuarioRepository;

    public ObtenerUsuarioPorUsernameHandler(IUsuarioRepository iUsuarioRepository) {
        this.iUsuarioRepository = iUsuarioRepository;
    }

    public UsuarioLogDTO handle(ObtenerUsuarioPorUsernameQuery query) {
        Optional<Usuario> usuario = iUsuarioRepository.findByUsernameNative(query.getUsername());
        return usuario.map(u -> new UsuarioLogDTO(u.getUsername(),u.getPassword(),u.getRol()))
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
    }
}
