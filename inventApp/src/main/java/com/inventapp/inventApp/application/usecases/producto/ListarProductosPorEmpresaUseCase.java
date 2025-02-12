package com.inventapp.inventApp.application.usecases.producto;

import com.inventapp.inventApp.application.queries.queries.producto.ListarProductosPorEmpresaQuery;
import com.inventapp.inventApp.domain.dtos.empresa.EmpresaDTO;
import com.inventapp.inventApp.domain.dtos.producto.ProductoDTO;
import com.inventapp.inventApp.domain.models.write.Producto;
import com.inventapp.inventApp.domain.repositories.producto.IProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarProductosPorEmpresaUseCase {

    private final IProductoRepository productoRepository;

    public List<ProductoDTO> ejecutar(ListarProductosPorEmpresaQuery query) {
        String empresaId = (query.getNit());

        if (empresaId == null) {
            throw new IllegalArgumentException("El ID de la empresa no puede ser nulo.");
        }

        List<Producto> productos = productoRepository.findByEmpresaNit(empresaId);

        if (productos.isEmpty()) {
            throw new RuntimeException("No se encontraron productos para la empresa con ID: " + empresaId);
        }

        return productos.stream()
                .map(ProductoDTO::new)
                .collect(Collectors.toList());
    }
}
