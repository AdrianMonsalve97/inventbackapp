package com.inventapp.inventApp.domain.repositories.usuario;
    import com.inventapp.inventApp.domain.enums.EstadoUsuario;
    import com.inventapp.inventApp.domain.enums.Rol;
    import com.inventapp.inventApp.domain.models.write.Usuario;

    import com.inventapp.inventApp.domain.repositories.usuario.IUsuarioRepository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.util.List;
    import java.util.Optional;
    import java.util.UUID;

@Repository
    public interface IUsuarioRepository  {

        Optional<Usuario> findByUsernameNative(@Param("username") String username);
        List<Usuario> findAll();
        Optional<Usuario> findByEmail(String email);
        boolean existsByUsername(String username);
        boolean existsByEmail(String email);
        Usuario save(Usuario usuario);
        List<Usuario> findByUsername(String username);
        List<Usuario> findByRol(Rol rol);
        List<Usuario> findByEstadoUsuario(EstadoUsuario estadoUsuario);
        List<Usuario> findByUsernameAndEmailAndRolAndEstadoUsuario(String username, String email, Rol rol, EstadoUsuario estadoUsuario);
        void deleteByUsername(String username);
        Optional<Usuario> findById(UUID id);

    }