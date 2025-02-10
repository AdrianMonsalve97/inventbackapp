package com.inventapp.inventApp.application.usecases.categoria;

import com.inventapp.inventApp.domain.dtos.categoria.CategoriaDTO;
import com.inventapp.inventApp.domain.repositories.categoria.ICategoriaRepository;
import com.inventapp.inventApp.infrastructure.mappers.categoria.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultarCategoriasUseCase {

    private final ICategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> ejecutar() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaDTO ejecutarPorId(UUID id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada."));
    }
}
