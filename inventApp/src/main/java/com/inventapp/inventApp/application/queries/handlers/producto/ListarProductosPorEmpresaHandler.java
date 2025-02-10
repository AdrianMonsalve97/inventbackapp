package com.inventapp.inventApp.application.queries.handlers.producto;

import com.inventapp.inventApp.application.queries.queries.producto.ListarProductosPorEmpresaQuery;
import com.inventapp.inventApp.application.usecases.producto.ListarProductosPorEmpresaUseCase;
import com.inventapp.inventApp.domain.dtos.producto.ProductoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarProductosPorEmpresaHandler {

    private final ListarProductosPorEmpresaUseCase listarProductosPorEmpresaUseCase;

    public List<ProductoDTO> ejecutar(ListarProductosPorEmpresaQuery query) {
        return listarProductosPorEmpresaUseCase.ejecutar(query);
    }
}
