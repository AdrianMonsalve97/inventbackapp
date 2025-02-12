package com.inventapp.inventApp.application.queries.handlers.usuario;

import com.inventapp.inventApp.application.queries.queries.usuario.ListarUsuariosQuery;
import com.inventapp.inventApp.domain.dtos.usuario.ListadoUsuariosDTO;
import com.inventapp.inventApp.domain.models.write.Usuario;
import com.inventapp.inventApp.domain.repositories.usuario.IUsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListarUsuariosHandlers {

    private final IUsuarioRepository usuarioRepository;

    public ListarUsuariosHandlers(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<ListadoUsuariosDTO> handle(ListarUsuariosQuery query) {
        List<ListadoUsuariosDTO> usuarios;
        if (query.getUsername() != null && query.getEmail() != null && query.getRol() != null
                && query.getEstadoUsuario() != null) {
            usuarios = usuarioRepository.findByUsernameAndEmailAndRolAndEstadoUsuario(
                    query.getUsername(), query.getEmail(), query.getRol(), query.getEstadoUsuario()).stream()
                    .map(this::convertToDTO).collect(Collectors.toList());
        } else if (query.getUsername() != null) {
            usuarios = usuarioRepository.findByUsername(query.getUsername())
                    .stream().map(this::convertToDTO).collect(Collectors.toList());
        } else if (query.getEmail() != null) {
            usuarios = usuarioRepository.findByEmail(query.getEmail())
                    .stream().map(this::convertToDTO).collect(Collectors.toList());
        } else if (query.getRol() != null) {
            usuarios = usuarioRepository.findByRol(query.getRol())
                    .stream().map(this::convertToDTO).collect(Collectors.toList());
        } else if (query.getEstadoUsuario() != null) {
            usuarios = usuarioRepository.findByEstadoUsuario(query.getEstadoUsuario())
                    .stream().map(this::convertToDTO).collect(Collectors.toList());
        } else {
            usuarios = usuarioRepository.findAll()
                    .stream().map(this::convertToDTO).collect(Collectors.toList());
        }

        return usuarios;
    }

    private ListadoUsuariosDTO convertToDTO(Usuario usuario) {
        return new ListadoUsuariosDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getEstadoUsuario());
    }
}