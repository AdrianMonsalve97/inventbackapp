package com.inventapp.inventApp.application.usecases.categoria;

import com.inventapp.inventApp.application.commands.command.categoria.ActualizarCategoriaCommand;
import com.inventapp.inventApp.domain.dtos.categoria.CategoriaDTO;
import com.inventapp.inventApp.domain.models.write.Categoria;
import com.inventapp.inventApp.domain.repositories.categoria.ICategoriaRepository;
import com.inventapp.inventApp.infrastructure.mappers.categoria.CategoriaMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActualizarCategoriaUseCase {

    private final ICategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Transactional
    public CategoriaDTO ejecutar(ActualizarCategoriaCommand command) {
        Categoria categoria = categoriaRepository.findById(command.getId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        categoria.setNombre(command.getNombre());

        categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoria);
    }
}
