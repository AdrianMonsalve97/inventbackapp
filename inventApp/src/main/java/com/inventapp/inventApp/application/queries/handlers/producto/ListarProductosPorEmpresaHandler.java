package com.inventapp.inventApp.application.queries.handlers.producto;

import com.inventapp.inventApp.application.queries.queries.producto.ListarProductosPorEmpresaQuery;
import com.inventapp.inventApp.domain.dtos.producto.ProductoDTO;
import com.inventapp.inventApp.domain.models.write.Empresa;
import com.inventapp.inventApp.domain.models.write.Producto;
import com.inventapp.inventApp.domain.repositories.empresa.IEmpresaRepository;
import com.inventapp.inventApp.domain.repositories.producto.IProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarProductosPorEmpresaHandler {

    private final IProductoRepository productoRepository;
    private final IEmpresaRepository empresaRepository;

    public List<ProductoDTO> ejecutar(ListarProductosPorEmpresaQuery query) {
        Empresa empresa = empresaRepository.findByNit(query.getNit())
                .orElseThrow(() -> new RuntimeException("No se encontró la empresa con el NIT: " + query.getNit()));

        List<Producto> productos = productoRepository.findByEmpresaId(empresa.getId());
        return productos.stream()
                .map(ProductoDTO::new)
                .collect(Collectors.toList());
    }
}
