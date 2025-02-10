package com.inventapp.inventApp.application.usecases.categoria;

import com.inventapp.inventApp.application.commands.command.categoria.CrearCategoriaCommand;
import com.inventapp.inventApp.domain.dtos.categoria.CategoriaDTO;
import com.inventapp.inventApp.domain.models.write.Categoria;
import com.inventapp.inventApp.domain.repositories.categoria.ICategoriaRepository;
import com.inventapp.inventApp.infrastructure.mappers.categoria.CategoriaMapper;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CrearCategoriaUseCase {

    private final ICategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Transactional
    public CategoriaDTO ejecutar(CrearCategoriaCommand command) {
        if (categoriaRepository.existsByNombre(command.getNombre())) {
            throw new EntityExistsException("Ya existe una categoría con este nombre.");
        }

        Categoria categoria = Categoria.builder()
                .nombre(command.getNombre())
                .build();

        categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoria);
    }
}
