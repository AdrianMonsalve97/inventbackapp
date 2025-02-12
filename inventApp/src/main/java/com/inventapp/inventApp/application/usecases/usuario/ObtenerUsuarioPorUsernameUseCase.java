package com.inventapp.inventApp.application.usecases.usuario;

        import com.inventapp.inventApp.domain.dtos.usuario.UsuarioLogDTO;
        import com.inventapp.inventApp.domain.exceptions.InvalidCredentialsException;
        import com.inventapp.inventApp.domain.models.write.Usuario;
        import com.inventapp.inventApp.domain.repositories.usuario.IUsuarioRepository;
        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Service;

        import java.util.Optional;

@Service
        @RequiredArgsConstructor
        public class ObtenerUsuarioPorUsernameUseCase {

            private final IUsuarioRepository usuarioRepository;

            public UsuarioLogDTO ejecutar(String username) {
                Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);

                if (usuarioOpt.isEmpty()) {
                    System.out.println("Usuario no encontrado: " + username);
                    return null;  // Devuelve null en lugar de lanzar una excepción
                }

                Usuario usuario = usuarioOpt.get();

                return UsuarioLogDTO.builder()
                        .id(usuario.getId())
                        .username(usuario.getUsername())
                        .password(usuario.getPassword())
                        .rol(usuario.getRol())
                        .build();
            }


        }