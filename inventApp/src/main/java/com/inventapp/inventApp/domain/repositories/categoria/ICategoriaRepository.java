package com.inventapp.inventApp.domain.repositories.categoria;

import com.inventapp.inventApp.domain.models.write.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICategoriaRepository extends JpaRepository<Categoria, UUID> {
    Optional<Categoria> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
