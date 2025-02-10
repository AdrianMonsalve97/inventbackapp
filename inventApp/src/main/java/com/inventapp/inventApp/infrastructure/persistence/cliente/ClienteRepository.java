package com.inventapp.inventApp.infrastructure.persistence.cliente;

import com.inventapp.inventApp.domain.models.write.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    List<Cliente> findAll();
    Optional<Cliente> findById(UUID id);
}