package com.inventapp.inventApp.application.usecases.usuario;

import com.inventapp.inventApp.application.commands.command.usuario.BorrarUsuarioCommand;
import com.inventapp.inventApp.domain.models.write.Usuario;
import com.inventapp.inventApp.domain.repositories.usuario.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrarUsuarioUseCase {

    private final IUsuarioRepository usuarioRepository;

    public void ejecutar(BorrarUsuarioCommand command) {
        Usuario usuario = usuarioRepository.findByUsernameNative(command.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioRepository.deleteByUsername(command.getUsername()); 
    }
}
