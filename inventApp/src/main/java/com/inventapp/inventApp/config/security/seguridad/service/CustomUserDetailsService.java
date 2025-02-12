package com.inventapp.inventApp.config.security.seguridad.service;
import com.inventapp.inventApp.domain.models.write.Usuario;
import com.inventapp.inventApp.infrastructure.persistence.usuario.repositories.writerepositories.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);

        Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        String role = usuario.getRol().toString().toUpperCase();

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(role)
                .build();
    }
}