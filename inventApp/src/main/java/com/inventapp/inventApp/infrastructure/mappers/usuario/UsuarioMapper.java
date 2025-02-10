package com.inventapp.inventApp.infrastructure.mappers.usuario;

import com.inventapp.inventApp.domain.dtos.usuario.UsuarioDTO;
import com.inventapp.inventApp.domain.models.write.Usuario;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId().toString())
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .nombre(usuario.getNombre())
                .build();
    }

    public Usuario toEntity(UsuarioDTO dto) {
        return Usuario.builder()
                .id(UUID.fromString(dto.getId()))
                .username(dto.getUsername())
                .email(dto.getEmail())
                .rol(dto.getRol())
                .nombre(dto.getNombre())
                .build();
    }
}
