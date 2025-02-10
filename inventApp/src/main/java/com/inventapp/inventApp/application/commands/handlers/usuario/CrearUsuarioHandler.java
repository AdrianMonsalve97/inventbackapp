package com.inventapp.inventApp.application.commands.handlers.usuario;

import com.inventapp.inventApp.application.commands.command.usuario.CrearUsuarioCommand;
import com.inventapp.inventApp.domain.models.write.Usuario;
import com.inventapp.inventApp.domain.repositories.usuario.IUsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrearUsuarioHandler {
    private final IUsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Usuario handle(CrearUsuarioCommand command) {
        Usuario usuario = buildUsuario(command);
        return usuarioRepository.save(usuario);
    }

    private Usuario buildUsuario(CrearUsuarioCommand command) {
        String hashedPassword = passwordEncoder.encode(command.getPassword());

        return Usuario.builder()
                .username(command.getUsername())
                .email(command.getEmail())
                .password(hashedPassword)
                .rol(command.getRol())
                .build();
    }
}
