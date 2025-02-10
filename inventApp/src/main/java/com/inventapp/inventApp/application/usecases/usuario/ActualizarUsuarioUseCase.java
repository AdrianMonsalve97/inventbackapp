package com.inventapp.inventApp.application.usecases.usuario;

import com.inventapp.inventApp.application.commands.command.usuario.ActualizarUsuarioCommand;
import com.inventapp.inventApp.domain.dtos.usuario.UsuarioDTO;
import com.inventapp.inventApp.domain.models.write.Usuario;
import com.inventapp.inventApp.domain.repositories.usuario.IUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActualizarUsuarioUseCase {

    private final IUsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioDTO ejecutar(ActualizarUsuarioCommand command) {

        Usuario usuario = usuarioRepository.findByUsernameNative(command.getUserName())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        usuario.setNombre(command.getName());
        usuario.setEmail(command.getEmail());
        usuario.setUsername(command.getUserName());

        usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }
}
