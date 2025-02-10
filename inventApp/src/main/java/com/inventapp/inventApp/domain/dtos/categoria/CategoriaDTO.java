package com.inventapp.inventApp.domain.dtos.categoria;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {
    private UUID id;
    private String nombre;
    private Set<UUID> productosIds;
}
