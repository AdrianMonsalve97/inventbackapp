package com.inventapp.inventApp.application.usecases.usuario;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventapp.inventApp.application.commands.command.usuario.CrearUsuarioCommand;
import com.inventapp.inventApp.domain.dtos.usuario.UsuarioDTO;
import com.inventapp.inventApp.domain.enums.EstadoUsuario;
import com.inventapp.inventApp.domain.models.write.Usuario;
import com.inventapp.inventApp.domain.repositories.usuario.IUsuarioRepository;
import com.inventapp.inventApp.infrastructure.mappers.usuario.UsuarioMapper;
import com.inventapp.inventApp.infrastructure.security.PasswordUtil;

@Service
public class CrearUsuarioUseCase {

    private final IUsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public CrearUsuarioUseCase(IUsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional
    public UsuarioDTO ejecutar(CrearUsuarioCommand command) {
        if (usuarioRepository.existsByUsername(command.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está registrado.");
        }

        if (usuarioRepository.existsByEmail(command.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }

        if (command.getPassword().length() < 8) {
            throw new RuntimeException("La contraseña debe tener al menos 8 caracteres.");
        }
        String hashedPassword = PasswordUtil.hashPassword(command.getPassword());

        Usuario usuario = Usuario.builder()
                .username(command.getUsername())
                .nombre(command.getNombre())
                .email(command.getEmail())
                .password(hashedPassword)
                .rol(command.getRol())
                .estadoUsuario(EstadoUsuario.ACTIVO)
                .build();

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuarioGuardado);
    }
}
