package com.inventapp.inventApp.application.usecases.usuario;

import com.inventapp.inventApp.domain.dtos.usuario.UsuarioLogDTO;
import com.inventapp.inventApp.domain.models.write.Usuario;
import com.inventapp.inventApp.domain.repositories.usuario.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObtenerUsuarioPorUsernameUseCase {

    private final IUsuarioRepository usuarioRepository;
    public UsuarioLogDTO ejecutar(String username) {
        Usuario u = usuarioRepository.findByUsernameNative(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return new UsuarioLogDTO(u.getUsername(), u.getPassword(), u.getRol());  // Aquí no necesitas codificar
    }

}