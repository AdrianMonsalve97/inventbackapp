package com.inventapp.inventApp.infrastructure.persistence.producto.repositories.writerepositories;

import com.inventapp.inventApp.domain.models.write.Producto;
import com.inventapp.inventApp.domain.repositories.producto.IProductoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, UUID>, IProductoRepository {
    List<Producto> findByEmpresaNombre(String nombre);
}
