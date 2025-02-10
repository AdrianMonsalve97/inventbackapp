package com.inventapp.inventApp.application.queries.queries.producto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ListarProductosPorEmpresaQuery {
    private final UUID empresaId;
}
