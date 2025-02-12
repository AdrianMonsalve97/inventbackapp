package com.inventapp.inventApp.application.queries.queries.producto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListarProductosPorEmpresaQuery {
    private String nit;  // Ahora buscamos por NIT, no UUID
}
