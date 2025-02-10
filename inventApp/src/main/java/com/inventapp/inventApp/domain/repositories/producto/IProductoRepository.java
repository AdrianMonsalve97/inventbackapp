package com.inventapp.inventApp.domain.repositories.producto;

import com.inventapp.inventApp.domain.models.write.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IProductoRepository extends JpaRepository<Producto, UUID> {
    boolean existsByCodigo(String codigo);
    List<Producto> findByEmpresaId(UUID empresaId);

    List<Producto> findByEmpresaNombre(String nombre);
}
