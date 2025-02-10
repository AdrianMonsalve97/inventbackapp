package com.inventapp.inventApp.infrastructure.persistence.orden;

import com.inventapp.inventApp.domain.models.write.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrdenRepository extends JpaRepository<Orden, UUID> {
    List<Orden> findByClienteId(UUID clienteId);
    Optional<Orden> findById(UUID id);
}
