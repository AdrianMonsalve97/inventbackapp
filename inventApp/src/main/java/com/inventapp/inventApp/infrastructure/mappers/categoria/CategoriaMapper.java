package com.inventapp.inventApp.infrastructure.mappers.categoria;

import com.inventapp.inventApp.domain.dtos.categoria.CategoriaDTO;
import com.inventapp.inventApp.domain.models.write.Categoria;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setId(dto.getId());
        categoria.setNombre(dto.getNombre());
        return categoria;
    }

    public CategoriaDTO toDTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .build();
    }

}
